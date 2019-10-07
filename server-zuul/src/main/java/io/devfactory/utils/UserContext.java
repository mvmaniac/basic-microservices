package io.devfactory.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class UserContext {

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    @Getter
    private String correlationId;

    @Getter
    private String authToken;

    @Getter
    private String userId;

    @Getter
    private String orgId;

    public void setInfo(String correlationId, String authToken, String userId, String orgId) {
        this.correlationId = correlationId;
        this.authToken = authToken;
        this.userId = userId;
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("correlationId", correlationId)
                .append("authToken", authToken)
                .append("userId", userId)
                .append("orgId", orgId)
                .toString();
    }

}
