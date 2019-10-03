package io.devfactory.license.model;

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

    public License withOrganizationId(String organizationId){
        this.organizationId = organizationId;
        return this;
    }

    public License withProductName(String productName){
        this.productName = productName;
        return this;
    }

    public License withLicenseType(String licenseType){
        this.licenseType = licenseType;
        return this;
    }

    public License withLicenseMax(Integer licenseMax){
        this.licenseMax = licenseMax;
        return this;
    }

    public License withLicenseAllocated(Integer licenseAllocated){
        this.licenseAllocated = licenseAllocated;
        return this;
    }

    public License withComment(String comment){
        this.comment = comment;
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
