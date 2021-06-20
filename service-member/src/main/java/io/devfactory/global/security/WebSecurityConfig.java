package io.devfactory.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.devfactory.global.security.jwt.JwtAuthenticationProcessingFilter;
import io.devfactory.global.security.jwt.JwtTokenProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String JWT_LOGIN_PROCESSING_URL = "/login";

  private final UserDetailsService userDetailsService;
  private final ObjectMapper objectMapper;

  private final JwtTokenProperties jwtTokenProperties;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .csrf()
        .disable()
      .authorizeRequests()
        .antMatchers("/members/**", JWT_LOGIN_PROCESSING_URL)
          .hasIpAddress("192.168.0.22")
        .and()

      .addFilterBefore(jwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)

      // h2-console 때문에...
      .headers()
        .frameOptions()
          .disable()
    ;
    // @formatter:on
  }

  @Bean
  public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() throws Exception {
    return new JwtAuthenticationProcessingFilter(JWT_LOGIN_PROCESSING_URL, authenticationManagerBean(), objectMapper, jwtTokenProperties);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
