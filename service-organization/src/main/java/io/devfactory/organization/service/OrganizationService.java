package io.devfactory.organization.service;

import io.devfactory.organization.model.Organization;
import io.devfactory.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrganizationService {

    private final OrganizationRepository orgRepository;

    public Organization getOrganization(String organizationId) {
        return orgRepository.findById(organizationId).orElseThrow(() -> new NullPointerException("organizationId: " + organizationId));
    }

    public void saveOrganization(Organization organization){
        organization.withId(UUID.randomUUID().toString());
        orgRepository.save(organization);
    }

    public void updateOrganization(Organization organization){
        orgRepository.save(organization);
    }

    public void deleteOrganization(String organizationId){
        orgRepository.deleteById(organizationId);
    }

}
