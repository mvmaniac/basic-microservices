package io.devfactory.global.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {

  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> circuitBreakerFactoryCustomizer() {
    final var timeLimiterConfig = TimeLimiterConfig.custom()
        // TimeLimiter는 future supplier의 time limit을 정하는 API
        // default: 1초 (1초 동안 응답이 없으면 문제가 있다고 판단하여 CirucitBreaker를 오픈
        // 4로 셋팅 했기 때문에 4초 동안 응답이 없으면 CirucitBreaker를 오픈
        .timeoutDuration(Duration.ofSeconds(4))
        .build();

    final var circuitBreakerConfig = CircuitBreakerConfig.custom()
        // CirucitBreaker를 열지 결정하는 failure rate threshold percentage
        // default: 50 (10번중 5번 실패하는 경우 오픈)
        // 4로 셋팅 했기 떄문에 100번 중 4번만 실패하는 경우 오픈
        .failureRateThreshold(4)

        // CirucitBreaker를 open한 상태를 유지하는 지속기간을 의미
        // 이 기간 이후에 half-open 상태
        // default: 60초 (60초 뒤에 다시 닫음)
        .waitDurationInOpenState(Duration.ofMillis(1000))

        // CirucitBreaker가 닫힐 때 통화 결과를 기록하는 데 사용되는 슬라이딩 창의 유형을 구성
        // 카운트 기반 또는 시간 기반
        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)

        // CirucitBreaker가 닫힐 때 호출 결과를 기록하는 데 사용되는 슬라이딩 창의 크기를 구성
        // default: 100 (카운트 기반인 경우에는 카운트, 시간 시간인 경우에는 시간)
        .slidingWindowSize(2)
        .build();

    return factory -> factory.configureDefault(
        id -> new Resilience4JConfigBuilder(id)
            .timeLimiterConfig(timeLimiterConfig)
            .circuitBreakerConfig(circuitBreakerConfig)
            .build()
    );
  }

}
