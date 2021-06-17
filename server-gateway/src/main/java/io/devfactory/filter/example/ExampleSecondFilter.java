package io.devfactory.filter.example;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Slf4j
@Component
public class ExampleSecondFilter extends AbstractGatewayFilterFactory<ExampleSecondFilter.Config> {

  public ExampleSecondFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return new OrderedGatewayFilter((exchange, chain) -> {
      final var request = exchange.getRequest();
      final var response = exchange.getResponse();
      log.info("Example second filter baseMessage: {}", config.getBaseMessage());

      if (config.isPreLogger()) {
        log.info("Example second filter start: request id -> {}", request.getId());
      }

      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        if (config.isPostLogger()) {
          log.info("Example second filter end: response code -> {}", response.getStatusCode());
        }
      }));
    }, HIGHEST_PRECEDENCE);
  }

  @Getter
  public static class Config {
    private final String baseMessage;
    private final boolean preLogger;
    private final boolean postLogger;

    public Config(String baseMessage, boolean preLogger, boolean postLogger) {
      this.baseMessage = baseMessage;
      this.preLogger = preLogger;
      this.postLogger = postLogger;
    }
  }

}
