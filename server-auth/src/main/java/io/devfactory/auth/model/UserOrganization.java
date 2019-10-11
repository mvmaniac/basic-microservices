package io.devfactory.auth.model;

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
@Table(name = "user_organizations")
public class UserOrganization {

    @Id
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    @Builder
    public UserOrganization(String userName, String organizationId) {
        this.userName = userName;
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("userName", userName)
                .append("organizationId", organizationId)
                .toString();
    }

}
