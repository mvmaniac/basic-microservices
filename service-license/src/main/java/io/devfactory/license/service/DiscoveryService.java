package io.devfactory.license.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiscoveryService {

    private final DiscoveryClient discoveryClient;

    public List<String> getEurekaServices() {
        List<String> services = new ArrayList<>();

        // TODO: 람다로 변경?
        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(serviceInstance -> {
                services.add(String.format("%s:%s", serviceName, serviceInstance.getUri()));
            });
        });

        return services;
    }

}
