package io.devfactory.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@Getter
public class AbTestingRoute {

    private String serviceName;
    private String active;
    private String endpoint;
    private Integer weight;

    @Builder
    public AbTestingRoute(String serviceName, String active, String endpoint, Integer weight) {
        this.serviceName = serviceName;
        this.active = active;
        this.endpoint = endpoint;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("serviceName", serviceName)
                .append("active", active)
                .append("endpoint", endpoint)
                .append("weight", weight)
                .toString();
    }
    
}