package io.devfactory.license.clients;

import io.devfactory.license.config.ServiceConfig;
import io.devfactory.license.model.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OrganizationDiscoveryClient {

    private final DiscoveryClient discoveryClient;

    private final ServiceConfig config;

    public Organization getOrganization(String organizationId) {

        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances(config.getServiceOrganizationId());

        if (instances.isEmpty()) {
            return null;
        }

        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);

        ResponseEntity<Organization> responseEntity =
                restTemplate.exchange(
                    serviceUri,
                    HttpMethod.GET,
                    null,
                    Organization.class,
                    organizationId
                );

        return responseEntity.getBody();
    }

}
