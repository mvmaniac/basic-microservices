package io.devfactory.license.events.handler;

import io.devfactory.license.events.CustomChannels;
import io.devfactory.license.events.model.OrganizationChangeModel;
import io.devfactory.license.repository.OrganizationRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {

    private final OrganizationRedisRepository organizationRedisRepository;

    @StreamListener("inboundOrganizationChange")
    public void logSink(OrganizationChangeModel organizationChangeModel) {
        log.debug("[dev] Received a message of type {}", organizationChangeModel.getType());

        switch(organizationChangeModel.getAction()){
            case "GET":
                log.debug("[dev] Received a GET event from the organization service for organization id {}", organizationChangeModel.getOrganizationId());
                break;
            case "SAVE":
                log.debug("[dev] Received a SAVE event from the organization service for organization id {}", organizationChangeModel.getOrganizationId());
                break;
            case "UPDATE":
                log.debug("[dev] Received a UPDATE event from the organization service for organization id {}", organizationChangeModel.getOrganizationId());
                organizationRedisRepository.deleteById(organizationChangeModel.getOrganizationId());
                break;
            case "DELETE":
                log.debug("[dev] Received a DELETE event from the organization service for organization id {}", organizationChangeModel.getOrganizationId());
                organizationRedisRepository.deleteById(organizationChangeModel.getOrganizationId());
                break;
            default:
                log.error("[dev] Received an UNKNOWN event from the organization service of type {}", organizationChangeModel.getType());
                break;
        }
    }

}
