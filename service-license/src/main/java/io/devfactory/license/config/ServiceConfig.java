package io.devfactory.license.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// TODO: ConfigurationProperties 사용?
@Getter
@Component
public class ServiceConfig{

  @Value("${example.property}")
  private String exampleProperty;

  @Value("${signing.key}")
  private String signingKey;

  @Value("${service.organization}")
  private String serviceOrganizationId;

  @Value("${service.zuul.organization}")
  private String serviceZuulOrganizationUrl;

}
