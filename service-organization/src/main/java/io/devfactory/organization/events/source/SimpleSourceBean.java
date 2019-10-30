package io.devfactory.organization.events.source;

import io.devfactory.organization.events.model.OrganizationChangeModel;
import io.devfactory.organization.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@EnableBinding({Source.class})
@Component
public class SimpleSourceBean {

    private final Source source;

    public void publishOrganizationChange(String action, String organizationId) {

        log.debug("[dev] Sending Kafka message {} for Organization Id: {}", action, organizationId);

        OrganizationChangeModel change = OrganizationChangeModel.builder()
                .type(OrganizationChangeModel.class.getTypeName())
                .action(action)
                .organizationId(organizationId)
                .correlationId(UserContextHolder.getContext().getCorrelationId())
                .build();

        source.output().send(MessageBuilder.withPayload(change).build());
    }

}
