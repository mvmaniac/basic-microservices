package io.devfactory.license.clients;

import io.devfactory.license.config.ServiceConfig;
import io.devfactory.license.model.Organization;
import io.devfactory.license.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrganizationRestTemplateClient {

    @Qualifier("restTemplateWithRibbon")
    private final RestTemplate restTemplate;

    private final ServiceConfig config;

    public Organization getOrganization(String organizationId) {
        log.debug(">>> In Licensing Service.getOrganization: {}. Thread Id: {}",
                UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());

        return restTemplate.exchange(
                String.format("http://%s/v1/organizations/{organizationId}", config.getServiceOrganizationId()),
                HttpMethod.GET,
                null, Organization.class, organizationId
        ).getBody();
    }

}
