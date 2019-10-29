package io.devfactory.organization.model;

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
@Table(name = "organizations_new")
public class Organization {

    @Id
    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    @Builder
    public Organization(String name, String contactName, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Organization withId(String id){
        this.organizationId = id;
        return this;
    }

    public Organization withPrefix(){
        this.name = "NEW::"+ this.name;
        this.contactName = "NEW::"+ contactName;
        this.contactEmail = "NEW::"+ contactEmail;
        this.contactPhone = "NEW::"+ contactPhone;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("organizationId", organizationId)
                .append("name", name)
                .append("contactName", contactName)
                .append("contactEmail", contactEmail)
                .append("contactPhone", contactPhone)
                .toString();
    }

}
