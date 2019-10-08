package io.devfactory.auth.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> user(OAuth2Authentication auth) {
        return Map.of(
            "user", auth.getUserAuthentication().getPrincipal(),
            "authorities", AuthorityUtils.authorityListToSet(auth.getUserAuthentication().getAuthorities())
        );
    }

}
