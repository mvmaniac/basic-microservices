package io.devfactory.license.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

// TODO: 공통으로 쓸 수 있는 방법? service-organization 과 다른 서비스에서도 같이?
@NoArgsConstructor
@Getter
@RedisHash("organization")
public class Organization {

    @Id
    private String organizationId; // javax.persistence.Id 로 하려면 변수명도 Id 이여야 함...
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

//    public Organization withId(String id){
//        this.organizationId = id;
//        return this;
//    }

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
