package io.devfactory.license.controller;

import io.devfactory.license.model.License;
import io.devfactory.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/organizations/{organizationId}/licenses")
@RestController
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }

    @GetMapping("/{licenseId}")
    public License getLicenses(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId, licenseId);
    }

    @PutMapping("/{licenseId}")
    public String updateLicenses(@PathVariable("licenseId") String licenseId) {
        return "This is the put";
    }

    @PostMapping
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{licenseId}")
    public String deleteLicenses(@PathVariable("licenseId") String licenseId) {
        return "This is the Delete";
    }

}
