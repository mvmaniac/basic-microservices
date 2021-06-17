package io.devfactory.web.api;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/service-order")
@RestController
public class OrderHealthCheckApi {

  private final Environment environment;

  public OrderHealthCheckApi(Environment environment) {
    this.environment = environment;
  }

  @GetMapping("/health-check")
  public String statusA() {
    return String.format("It's Working in order service on PORT: %s", environment.getProperty("local.server.port"));
  }

}
