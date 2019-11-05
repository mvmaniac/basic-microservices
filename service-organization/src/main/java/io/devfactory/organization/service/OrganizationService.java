package io.devfactory.organization.service;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;
import io.devfactory.organization.events.source.SimpleSourceBean;
import io.devfactory.organization.model.Organization;
import io.devfactory.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrganizationService {

    private final OrganizationRepository orgRepository;

    private final SimpleSourceBean simpleSourceBean;

    private final Tracer tracer;

    public Organization getOrganization(String organizationId) {

        Span newSpan = tracer.nextSpan().name("getOrganizationByDB");

        try (SpanInScope spanInScope = tracer.withSpanInScope(newSpan.start())) {
            return orgRepository.findById(organizationId).orElseThrow(() -> new NullPointerException("organizationId: " + organizationId));
        } finally {
            newSpan.tag("peer.service", "postgres");
            newSpan.annotate("cr");
            newSpan.finish();
        }
    }

    public void saveOrganization(Organization organization){
        organization.withId(UUID.randomUUID().toString());
        orgRepository.save(organization);

        simpleSourceBean.publishOrganizationChange("SAVE", organization.getOrganizationId());
    }

    public void updateOrganization(Organization organization){
        orgRepository.save(organization);

        simpleSourceBean.publishOrganizationChange("UPDATE", organization.getOrganizationId());
    }

    public void deleteOrganization(String organizationId){
        orgRepository.deleteById(organizationId);

        simpleSourceBean.publishOrganizationChange("DELETE", organizationId);
    }

}
