package io.devfactory.license.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {

    @Input("inboundOrganizationChange")
    SubscribableChannel organization();

}
