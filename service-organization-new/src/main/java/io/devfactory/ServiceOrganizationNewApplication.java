package io.devfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ServiceOrganizationNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrganizationNewApplication.class, args);
    }

}
