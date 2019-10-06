package io.devfactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@EnableDiscoveryClient // discoveryClient 를 사용 할 경우
@EnableFeignClients // feignClient 를 사용 할 경우
@SpringBootApplication
public class ServiceLicenseApplication {

    // TODO: 페인으로도 적용이 되는지 확인
    @Qualifier("restTemplateWithRibbon")
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplateWithRibbon() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceLicenseApplication.class, args);
    }

}
