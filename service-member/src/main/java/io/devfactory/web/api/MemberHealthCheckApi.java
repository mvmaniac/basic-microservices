package io.devfactory.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberHealthCheckApi {

  @GetMapping("/health-check")
  public String statusA() {
    return "It's Working in member service.";
  }

}
