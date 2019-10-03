package io.devfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@SpringBootApplication
public class ServiceLicenseApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ServiceLicenseApplication.class, args);
    }

}
