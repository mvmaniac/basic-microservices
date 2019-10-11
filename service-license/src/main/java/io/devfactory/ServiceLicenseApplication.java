package io.devfactory;

import io.devfactory.license.utils.UserContextInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RefreshScope
@EnableDiscoveryClient // discoveryClient 를 사용 할 경우
@EnableFeignClients // feignClient 를 사용 할 경우
@EnableCircuitBreaker
@EnableResourceServer
@SpringBootApplication
public class ServiceLicenseApplication {

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(UserInfoRestTemplateFactory factory) {
        return factory.getUserInfoRestTemplate();
    }

    // TODO: 페인으로도 적용이 되는지 확인
    @LoadBalanced
    @Primary
    @Bean
    public RestTemplate getRestTemplateWithRibbon() {

        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        if (CollectionUtils.isEmpty(interceptors)) {
            restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            restTemplate.setInterceptors(interceptors);
        }

        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceLicenseApplication.class, args);
    }

}
