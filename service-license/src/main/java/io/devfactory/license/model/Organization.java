package io.devfactory.license.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@Getter
public class Organization {

    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

    @Builder
    public Organization(String name, String contactName, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("name", name)
                .append("contactName", contactName)
                .append("contactEmail", contactEmail)
                .append("contactPhone", contactPhone)
                .toString();
    }

}
