package io.devfactory.organization.controller;

import io.devfactory.organization.model.Organization;
import io.devfactory.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/organizations")
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/{organizationId}")
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.getOrganization(organizationId);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    @PutMapping("/{organizationId}")
    public void updateOrganization(@PathVariable("organizationId") String organizationId, @RequestBody Organization org) {
        organizationService.updateOrganization(org);
    }

    @PostMapping()
    public void saveOrganization(@RequestBody Organization org) {
        organizationService.saveOrganization(org);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{organizationId}")
    public void deleteOrganization(@PathVariable("organizationId") String organizationId, @RequestBody Organization org) {
        organizationService.deleteOrganization(org);
    }

}
