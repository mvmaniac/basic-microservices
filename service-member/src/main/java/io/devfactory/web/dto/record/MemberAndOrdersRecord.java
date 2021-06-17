package io.devfactory.web.dto.record;

import io.devfactory.web.domain.Member;

import java.util.List;

public record MemberAndOrdersRecord(Member member, List<OrderRecord> orders) {

}
