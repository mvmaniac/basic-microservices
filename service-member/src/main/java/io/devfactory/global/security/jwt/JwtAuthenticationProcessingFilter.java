package io.devfactory.global.security.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.devfactory.global.security.principal.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

  private final ObjectMapper objectMapper;
  private final JwtTokenProperties jwtTokenProperties;

  public JwtAuthenticationProcessingFilter(String loginProcessingUrl,
      AuthenticationManager authenticationManager, ObjectMapper objectMapper,
      JwtTokenProperties jwtTokenProperties) {
    super(loginProcessingUrl, authenticationManager);

    this.objectMapper = objectMapper;
    this.jwtTokenProperties = jwtTokenProperties;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException {

    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    }

    JwtAuthRequest jwtAuthRequest;

    try {
      jwtAuthRequest = objectMapper.readValue(request.getInputStream(), JwtAuthRequest.class);
    } catch (JsonParseException | JsonMappingException e) {
      log.error("JwtAuthenticationProcessingFilter.attemptAuthentication: {}", e.getMessage(), e);
      throw new BadCredentialsException("BadCredentialsException");
    }

    final var authRequest = new UsernamePasswordAuthenticationToken(jwtAuthRequest.email(), jwtAuthRequest.password());
    setDetails(request, authRequest); // Allow subclasses to set the "details" property
    return this.getAuthenticationManager().authenticate(authRequest);
  }

  private void setDetails(HttpServletRequest request,
      UsernamePasswordAuthenticationToken authRequest) {
    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) {
    final var member = ((PrincipalDetails) authResult.getPrincipal()).getMember();

    // TODO: Jwt 클래스화
    final var uniqueId = "uniqueId";
    final var now = new Date();
    final var keyBytes = Decoders.BASE64.decode(jwtTokenProperties.getClientSecret());

    final var jwtToken = Jwts.builder()
        .setSubject(member.getEmail())
        .setIssuer(jwtTokenProperties.getIssuer())
        .setIssuedAt(now)
        .setExpiration(new Date((now.getTime() + jwtTokenProperties.getExpires())))
        .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS512)
        .claim(uniqueId, member.getUniqueId())
        .compact();

    response.addHeader(jwtTokenProperties.getHeader(), jwtToken);
    response.addHeader(uniqueId, member.getUniqueId());
  }

}
