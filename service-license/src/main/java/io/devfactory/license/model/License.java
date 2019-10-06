package io.devfactory.license.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "licenses")
public class License {

    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_max", nullable = false)
    private int licenseMax;

    @Column(name = "license_allocated", nullable = false)
    private int licenseAllocated;

    @Column(name="comment")
    private String comment;

    @Transient
    private String organizationName = "";

    @Transient
    private String contactName = "";

    @Transient
    private String contactPhone = "";

    @Transient
    private String contactEmail = "";

    @Builder
    public License(String organizationId, String productName, String licenseType, int licenseMax, int licenseAllocated, String comment) {
        this.organizationId = organizationId;
        this.productName = productName;
        this.licenseType = licenseType;
        this.licenseMax = licenseMax;
        this.licenseAllocated = licenseAllocated;
        this.comment = comment;
    }

    public License withId(String id){
        this.licenseId = id;
        return this;
    }

    public License withComment(String comment){
        this.comment = comment;
        return this;
    }

    public License withOrganization(Organization organization){
        this.organizationName = organization.getName();
        this.contactName = organization.getContactName();
        this.contactPhone = organization.getContactPhone();
        this.contactEmail = organization.getContactEmail();
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("licenseId", licenseId)
                .append("organizationId", organizationId)
                .append("productName", productName)
                .append("licenseType", licenseType)
                .append("licenseMax", licenseMax)
                .append("licenseAllocated", licenseAllocated)
                .append("comment", comment)
                .toString();
    }

}
