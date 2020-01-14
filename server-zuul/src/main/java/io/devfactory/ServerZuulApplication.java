package io.devfactory;

import io.devfactory.util.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RefreshScope
@EnableZuulProxy
@SpringBootApplication
public class ServerZuulApplication {

    @LoadBalanced
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

        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(ServerZuulApplication.class, args);
    }

}
