package io.devfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberApplicationRunner implements ApplicationRunner {

  private final Environment environment;

  @Value("${spring.application.name}")
  private String applicationName;

  public MemberApplicationRunner(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void run(ApplicationArguments args) {
    log.debug("[dev] spring.application.name: {}", applicationName);
    
    final var vmCustom = environment.getProperty("vm.custom");
    log.debug("[dev] vm.custom: {}", vmCustom);

    final var progCustom = args.getOptionValues("prog.custom");
    log.debug("[dev] prog.custom: {}", progCustom);
  }

}
