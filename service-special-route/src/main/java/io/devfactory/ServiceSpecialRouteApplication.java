package io.devfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ServiceSpecialRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSpecialRouteApplication.class, args);
    }

}
