package io.devfactory.license.controller;

import io.devfactory.license.model.License;
import io.devfactory.license.service.LicenseService;
import io.devfactory.license.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/organizations/{organizationId}/licenses")
@RestController
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        log.debug("LicenseController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return licenseService.getLicensesByOrganizationId(organizationId);
    }

    @GetMapping("/{licenseId}")
    public License getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {

        return licenseService.getLicense(organizationId, licenseId, "");
    }

    @GetMapping("/{licenseId}/{clientType}")
    public License getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @PathVariable("clientType") String clientType) {

        return licenseService.getLicense(organizationId, licenseId, clientType);
    }

    @PutMapping("/{licenseId}")
    public String updateLicense(@PathVariable("licenseId") String licenseId) {
        return "This is the put";
    }

    @PostMapping
    public void saveLicense(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{licenseId}")
    public String deleteLicense(@PathVariable("licenseId") String licenseId) {
        return "This is the Delete";
    }

}
