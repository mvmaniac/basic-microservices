package io.devfactory.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ConfigurationPropertiesScan("io.devfactory.global")
@Configuration
public class AppConfig {

}
