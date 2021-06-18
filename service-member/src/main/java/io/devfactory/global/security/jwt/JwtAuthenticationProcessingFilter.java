package io.devfactory.global.security.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.devfactory.global.security.principal.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

  private final ObjectMapper objectMapper;

  public JwtAuthenticationProcessingFilter(String loginProcessingUrl,
      AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
    super(loginProcessingUrl, authenticationManager);
    this.objectMapper = objectMapper;
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
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    final var member = ((PrincipalDetails) authResult.getPrincipal()).getMember();

  }

}
