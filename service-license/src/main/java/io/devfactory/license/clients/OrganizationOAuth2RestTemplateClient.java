package io.devfactory.license.clients;

import io.devfactory.license.config.ServiceConfig;
import io.devfactory.license.model.Organization;
import io.devfactory.license.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrganizationOAuth2RestTemplateClient {

    private final OAuth2RestTemplate restTemplate;

    private final ServiceConfig config;

    public Organization getOrganization(String organizationId) {
        log.debug(">>> In Licensing Service.getOrganization: {}. Thread Id: {}",
                UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());

        return restTemplate.exchange(
                String.format("http://%s/v1/organizations/{organizationId}", config.getServiceZuulOrganizationUrl()),
                HttpMethod.GET,
                null, Organization.class, organizationId
        ).getBody();
    }

}
