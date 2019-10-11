package io.devfactory.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// TODO: ConfigurationProperties 사용?
@Getter
@Component
public class ServiceConfig {

  @Value("${signing.key}")
  private String signingKey;

}
