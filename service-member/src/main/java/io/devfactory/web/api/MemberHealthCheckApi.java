package io.devfactory.web.api;

import io.micrometer.core.annotation.Timed;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberHealthCheckApi {

  private final Environment environment;

  public MemberHealthCheckApi(Environment environment) {
    this.environment = environment;
  }

  @Timed(value = "member.health.check", longTask = true)
  @GetMapping("/health-check")
  public String statusA() {
    // environment로 설정 파일 값을 가지고 와야 변경된 설정 파일 값을 동적으로 읽을 수 있을 듯?
    // 만약 동적으로 값 변경이 필요하다면 @ConfigurationProperties로는 안될 듯?
    return String.format("It's Working in member service on PORT: %s, server.port: %s, jwt.token.client-secret: %s, jwt.token.expires: %s",
        environment.getProperty("local.server.port"),
        environment.getProperty("server.port"),
        environment.getProperty("jwt.token.client-secret"),
        environment.getProperty("jwt.token.expires"));
  }

}
