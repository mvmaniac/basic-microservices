package io.devfactory.web.api;

import io.micrometer.core.annotation.Timed;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderHealthCheckApi {

  private final Environment environment;

  public OrderHealthCheckApi(Environment environment) {
    this.environment = environment;
  }

  @Timed(value = "order.health.check", longTask = true)
  @GetMapping("/health-check")
  public String statusA() {
    return String.format("It's Working in order service on PORT: %s", environment.getProperty("local.server.port"));
  }

}
