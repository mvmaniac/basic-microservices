package io.devfactory.web.api;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogHealthCheckApi {

  private final Environment environment;

  public CatalogHealthCheckApi(Environment environment) {
    this.environment = environment;
  }

  @GetMapping("/health-check")
  public String statusA() {
    return String.format("It's Working in catalog service on PORT: %s", environment.getProperty("local.server.port"));
  }

}
