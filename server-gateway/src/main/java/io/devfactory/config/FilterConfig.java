package io.devfactory.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

//@Configuration
public class FilterConfig {

  // route 설정을 yaml 파일이 아닌 자바에서 설정하는 경우
//  @Bean
  public RouteLocator serviceRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
          .route(getRoute("/first-service/**", "http://localhost:8081", getFirstServiceFiler()))
          .route(getRoute("/second-service/**", "http://localhost:8082", getSecondServiceFiler()))
          .build()
        ;
  }

  private Function<PredicateSpec, Buildable<Route>> getRoute(String path, String  uri, Function<GatewayFilterSpec, UriSpec> filters) {
    return predicateSpec -> predicateSpec.path(path).filters(filters).uri(uri);
  }

  private Function<GatewayFilterSpec, UriSpec> getFirstServiceFiler() {
    return filterSpec -> filterSpec.addRequestHeader("first-request", "first-request-header")
        .addResponseHeader("first-response", "first-response-header");
  }

  private Function<GatewayFilterSpec, UriSpec> getSecondServiceFiler() {
    return filterSpec -> filterSpec.addRequestHeader("second-request", "second-request-header")
        .addResponseHeader("second-response", "second-response-header");
  }

}
