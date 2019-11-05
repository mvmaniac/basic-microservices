package io.devfactory.license.clients;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;
import io.devfactory.license.config.ServiceConfig;
import io.devfactory.license.model.Organization;
import io.devfactory.license.repository.OrganizationRedisRepository;
import io.devfactory.license.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrganizationRestTemplateClient {

    private final RestTemplate restTemplate;

    private final OrganizationRedisRepository organizationRedisRepository;

    private final Tracer tracer;

    private final ServiceConfig config;

    public Organization getOrganization(String organizationId) {
        log.debug("[dev] In Licensing Service.getOrganization: {}. Thread Id: {}",
                UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());

        Organization organization = checkRedisCache(organizationId);

        if (!StringUtils.isEmpty(organization.getOrganizationId())) {
            log.debug("[dev] I have successfully retrieved an organization {} from the redis cache: {}", organizationId, organization);
            return organization;
        }

        log.debug("[dev] Unable to locate organization from the redis cache: {}.", organizationId);

        organization = restTemplate.exchange(
                String.format("http://%s/v1/organizations/{organizationId}", config.getServiceZuulOrganizationUrl()),
                HttpMethod.GET,
                null, Organization.class, organizationId
        ).getBody();

        if (null != organization) {
            cacheOrganizationObject(organization);
        }

        return organization;
    }

    private Organization checkRedisCache(String organizationId) {

        Span newSpan = tracer.nextSpan().name("readLicenseDataFromRedis");

        try(SpanInScope spanInScope = tracer.withSpanInScope(newSpan.start())) {
            return organizationRedisRepository.findById(organizationId).orElse(new Organization());
        } catch (Exception e) {
            log.error("Error encountered while trying to retrieve organization {} check Redis Cache. Exception {}", organizationId, e);
            return new Organization();
        } finally {
            newSpan.tag("peer.service", "redis");
            newSpan.annotate("cr");
            newSpan.finish();
        }
    }

    private void cacheOrganizationObject(Organization organization) {
        try {
            organizationRedisRepository.save(organization);
        } catch (Exception e) {
            log.error("Unable to cache organization {} in Redis. Exception {}", organization.getOrganizationId(), e);
        }
    }

}
