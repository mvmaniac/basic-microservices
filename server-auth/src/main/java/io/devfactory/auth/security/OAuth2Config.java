package io.devfactory.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

// JWTOAuth2Config 사용함
//@RequiredArgsConstructor
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("dev-client")
//                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("secret-key"))
//                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
//                .scopes("web-client", "mobile-client");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints
//                .authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);
//    }
//
//}
