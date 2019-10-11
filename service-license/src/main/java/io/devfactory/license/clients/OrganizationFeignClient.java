package io.devfactory.license.clients;

import io.devfactory.license.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${service.zuul.organization}")
public interface OrganizationFeignClient {

    // TODO: RequestLine?, GetMapping 사용?
    // TODO: token 처리...
    @RequestMapping(method = RequestMethod.GET, value = "/v1/organizations/{organizationId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Organization getOrganization(@RequestHeader("Authorization") String bearerToken, @PathVariable("organizationId") String organizationId);

}
