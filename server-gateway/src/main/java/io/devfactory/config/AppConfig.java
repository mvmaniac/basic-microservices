package io.devfactory.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ConfigurationPropertiesScan("io.devfactory.config")
@Configuration
public class AppConfig {

}
