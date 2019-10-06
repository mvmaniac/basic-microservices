package io.devfactory.license.service;

import io.devfactory.license.clients.OrganizationDiscoveryClient;
import io.devfactory.license.clients.OrganizationRestTemplateClient;
import io.devfactory.license.clients.OrganizationFeignClient;
import io.devfactory.license.config.ServiceConfig;
import io.devfactory.license.model.License;
import io.devfactory.license.model.Organization;
import io.devfactory.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    private final OrganizationDiscoveryClient discoveryClient;
    private final OrganizationFeignClient restTemplateClient;
    private final OrganizationRestTemplateClient feignClient;

    private final ServiceConfig config;

    public List<License> getLicensesByOrganizationId(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public License getLicense(String organizationId, String licenseId, String clientType) {

        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        license.withComment(config.getExampleProperty());

        return retrieveOrganizationInfo(organizationId, clientType)
                .map(license::withOrganization).orElse(license);
    }

    public void saveLicense(License license) {
        license.withId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

    // TODO: 에러 처리
    // TODO: clientType enum 으로...
    private Optional<Organization> retrieveOrganizationInfo(String organizationId, String clientType) {

        Organization organization;

        switch (clientType) {
            case "feign":
                log.debug("I am using the feign client");
                organization = feignClient.getOrganization(organizationId);
                break;

            case "rest":
                log.debug("I am using the rest client");
                organization = restTemplateClient.getOrganization(organizationId);
                break;

            case "discovery":
                log.debug("I am using the discovery client");
                organization = discoveryClient.getOrganization(organizationId);
                break;

            default:
                organization = restTemplateClient.getOrganization(organizationId);

        }

        return Optional.ofNullable(organization);
    }

}
