package io.devfactory.filter;

import io.devfactory.config.jwt.JwtTokenProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.util.StringUtils.hasLength;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

  private static final String BEARER = "Bearer ";

  private final JwtTokenProperties jwtTokenProperties;

  public AuthorizationHeaderFilter(JwtTokenProperties jwtTokenProperties) {
    super(Config.class);
    this.jwtTokenProperties = jwtTokenProperties;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      final var request = exchange.getRequest();

      final var authorizationToken = obtainAuthorizationToken(request);

      if (isNull(authorizationToken)) {
        return onErrorUnauthorized(exchange, "No authorization header");
      }

      if (!isJwtValid(authorizationToken)) {
        return onErrorUnauthorized(exchange, "JWT token is not valid");
      }

      return chain.filter(exchange);
    };
  }

  private boolean isJwtValid(String authorizationToken) {
    final var keyBytes = Decoders.BASE64.decode(jwtTokenProperties.getClientSecret());
    final var uniqueId = Jwts.parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
        .build()
        .parseClaimsJws(authorizationToken)
        .getBody()
        .get("uniqueId", String.class);

    // TODO: 값이 맞는지 확인?
    return hasLength(uniqueId);
  }

  private Mono<Void> onErrorUnauthorized(ServerWebExchange exchange, String message) {
    log.error(message);

    final var response = exchange.getResponse();
    response.setStatusCode(UNAUTHORIZED);
    return response.setComplete();
  }

  @Nullable
  private String obtainAuthorizationToken(ServerHttpRequest request) {
    final var headers = request.getHeaders().get(jwtTokenProperties.getHeader());

    if (isNull(headers)) {
      return null;
    }

    var token = headers.get(0);

    try {
      token = URLDecoder.decode(token, StandardCharsets.UTF_8.name());

      if (!token.startsWith(BEARER)) {
        return null;
      }
      return token.replace(BEARER, "");

    } catch (UnsupportedEncodingException e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }

  @Getter
  public static class Config {

  }

}
