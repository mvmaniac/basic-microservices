package io.devfactory.auth.security;

import io.devfactory.auth.model.UserOrganization;
import io.devfactory.auth.repository.UserOrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Primary
@Component
public class JWTTokenEnhancer implements TokenEnhancer {

    private final UserOrganizationRepository userOrganizationRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = Map.of("organizationId", getOrganizationId(authentication.getName()));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

    private String getOrganizationId(String userName) {
        return userOrganizationRepository.findByUserName(userName)
                .map(UserOrganization::getOrganizationId)
                .orElse("");
    }

}
