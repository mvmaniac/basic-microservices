package io.devfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ServiceOrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrganizationApplication.class, args);
    }

}
