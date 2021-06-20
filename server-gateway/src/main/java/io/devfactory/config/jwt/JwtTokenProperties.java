package io.devfactory.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Getter
@ConstructorBinding
@ConfigurationProperties("jwt.token")
public class JwtTokenProperties {

  private final String header;

  private final String issuer;

  @DurationUnit(MINUTES)
  private final Duration expires;

  @DurationUnit(MINUTES)
  private final Duration refreshRange;

  private final String clientSecret;

  public long getExpires() {
    return expires.toMillis();
  }

  public long getRefreshRange() {
    return refreshRange.toMillis();
  }

}
