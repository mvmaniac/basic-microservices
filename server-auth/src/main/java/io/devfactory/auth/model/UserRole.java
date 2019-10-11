package io.devfactory.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id", columnDefinition = "serial")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "role", nullable = false)
    private String role;

    @Builder
    public UserRole(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("userName", userName)
                .append("role", role)
                .toString();
    }

}
