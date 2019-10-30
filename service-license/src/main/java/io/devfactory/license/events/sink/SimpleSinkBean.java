package io.devfactory.license.events.sink;

import io.devfactory.license.events.model.OrganizationChangeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Slf4j
@EnableBinding({Sink.class})
@Component
public class SimpleSinkBean {

    @StreamListener(Sink.INPUT)
    public void subscribeOrganizationChange(OrganizationChangeModel change) {
        log.debug("[dev] Received an event for Organization Id: {}", change);
    }

}
