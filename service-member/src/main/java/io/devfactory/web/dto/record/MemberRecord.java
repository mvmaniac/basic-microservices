package io.devfactory.web.dto.record;

import java.time.LocalDateTime;

public record MemberRecord(long id, String uniqueId, String email, String name, LocalDateTime createdDate) {

}
