package io.devfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableConfigServer
@SpringBootApplication
public class ServerConfigApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ServerConfigApplication.class, args);
    }

}
