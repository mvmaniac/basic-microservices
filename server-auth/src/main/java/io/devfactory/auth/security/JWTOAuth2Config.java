package io.devfactory.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final TokenStore tokenStore;
    private final DefaultTokenServices tokenServices;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("dev-client")
                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("secret-key"))
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("web-client", "mobile-client");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));

        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

}
