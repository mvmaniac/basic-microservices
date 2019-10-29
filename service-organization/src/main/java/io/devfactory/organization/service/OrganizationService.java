package io.devfactory.organization.service;

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

    public Organization getOrganization(String organizationId) {
        return orgRepository.findById(organizationId).orElseThrow(() -> new NullPointerException("organizationId: " + organizationId));
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
