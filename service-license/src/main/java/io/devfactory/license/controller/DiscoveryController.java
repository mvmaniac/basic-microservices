package io.devfactory.license.controller;

import io.devfactory.license.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/tools")
@RestController
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    @GetMapping("/eureka/services")
    public List<String> getEurekaServices() {
        return discoveryService.getEurekaServices();
    }

}
