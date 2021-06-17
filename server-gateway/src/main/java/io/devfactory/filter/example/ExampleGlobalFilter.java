package io.devfactory.filter.example;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExampleGlobalFilter extends AbstractGatewayFilterFactory<ExampleGlobalFilter.Config> {

  public ExampleGlobalFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      final var request = exchange.getRequest();
      final var response = exchange.getResponse();
      log.info("Example global filter baseMessage: {}", config.getBaseMessage());

      if (config.isPreLogger()) {
        log.info("Example global filter start: request id -> {}", request.getId());
      }

      return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        if (config.isPostLogger()) {
          log.info("Example global filter end: response code -> {}", response.getStatusCode());
        }
      }));
    };
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
