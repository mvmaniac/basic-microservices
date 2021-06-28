package io.devfactory.global;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ConfigurationPropertiesScan("io.devfactory.global")
@Configuration
public class AppConfig {

  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  // FeignClient 로그 출력
  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

}
