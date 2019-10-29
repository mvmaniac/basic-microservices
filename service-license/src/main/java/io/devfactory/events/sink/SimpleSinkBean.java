package io.devfactory.events.sink;

import io.devfactory.events.model.OrganizationChangeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleSinkBean {

    @StreamListener(Sink.INPUT)
    public void subscribeOrganizationChange(OrganizationChangeModel change) {
        log.debug("[dev] Received an event for Organization Id: {}", change);
    }

}
