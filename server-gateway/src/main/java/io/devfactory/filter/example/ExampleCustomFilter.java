package io.devfactory.filter.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExampleCustomFilter extends AbstractGatewayFilterFactory<ExampleCustomFilter.Config> {

  public ExampleCustomFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      final var request = exchange.getRequest();
      final var response = exchange.getResponse();
      log.info("Example Custom BEFORE filter: request id -> {}", request.getId());
      return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("Example Custom AFTER filter: response code -> {}", response.getStatusCode())));
    };
  }

  public static class Config {
    // Put the configuration properties
  }

}
