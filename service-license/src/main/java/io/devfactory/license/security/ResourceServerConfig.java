package io.devfactory.license.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
//                .mvcMatchers("/*/organizations/**")
//                    .hasRole("ADMIN")
                .anyRequest()
                    .authenticated();
        // @formatter:on
    }

}
