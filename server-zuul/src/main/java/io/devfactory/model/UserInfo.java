package io.devfactory.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@NoArgsConstructor
@Getter
public class UserInfo {

    private String organizationId;
    private String userId;

    @Builder
    public UserInfo(String organizationId, String userId) {
        this.organizationId = organizationId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("organizationId", organizationId)
                .append("userId", userId)
                .toString();
    }

}
