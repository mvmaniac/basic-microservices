package io.devfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ServiceTestApi {

  private final Environment environment;

  public ServiceTestApi(Environment environment) {
    this.environment = environment;
  }

  @GetMapping("/first-service/welcome")
  public String first() {
    return "Welcome to the First service.";
  }

  @SuppressWarnings("UastIncorrectHttpHeaderInspection")
  @GetMapping("/first-service/message")
  public String firstMessage(@RequestHeader("first-request") String headerValue) {
    log.debug("[dev] headerValue: {}", headerValue);
    return "Welcome to the First service message";
  }

  @GetMapping("/first-service/check")
  public String firstCheck(HttpServletRequest request) {
    log.info("Server port = {}", request.getServerPort());
    // 랜덤포트 번호는 local.server.port 에서 가져올 수 있음
    return String.format("Hi, there, This is message from first service on PORT %s", environment.getProperty("local.server.port"));
  }

  @GetMapping("/second-service/welcome")
  public String second() {
    return "Welcome to the Second service.";
  }

  @SuppressWarnings("UastIncorrectHttpHeaderInspection")
  @GetMapping("/second-service/message")
  public String secondMessage(@RequestHeader("second-request") String headerValue) {
    log.debug("[dev] headerValue: {}", headerValue);
    return "Welcome to the Second service message.";
  }

  @GetMapping("/second-service/check")
  public String secondCheck(HttpServletRequest request) {
    log.info("Server port = {}", request.getServerPort());
    // 랜덤포트 번호는 local.server.port 에서 가져올 수 있음
    return String.format("Hi, there, This is message from second service on PORT %s", environment.getProperty("local.server.port"));
  }

}
