package io.devfactory.specialroute.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "special_route")
public class SpecialRoute {

    @Id
    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name="active", nullable = false)
    private String active;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Builder
    public SpecialRoute(String serviceName, String active, String endpoint, Integer weight) {
        this.serviceName = serviceName;
        this.active = active;
        this.endpoint = endpoint;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("serviceName", serviceName)
                .append("active", active)
                .append("endpoint", endpoint)
                .append("weight", weight)
                .toString();
    }

}
