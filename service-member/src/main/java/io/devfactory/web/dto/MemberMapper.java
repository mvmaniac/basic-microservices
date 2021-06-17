package io.devfactory.web.dto;

import io.devfactory.web.domain.Member;
import io.devfactory.web.dto.record.MemberAndOrdersRecord;
import io.devfactory.web.dto.request.MemberRequestView;
import io.devfactory.web.dto.response.MemberResponseView;
import io.devfactory.web.dto.response.MemberWithOrdersResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = OrderMapper.class)
public interface MemberMapper {

  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  Member requestViewOf(MemberRequestView requestView);

  MemberResponseView toResponseView(Member member);

  List<MemberResponseView> toResponseViews(List<Member> members);

  @Mapping(source = "member.uniqueId", target = "uniqueId")
  @Mapping(source = "member.email", target = "email")
  @Mapping(source = "member.name", target = "name")
  MemberWithOrdersResponseView recordToView(MemberAndOrdersRecord memberAndOrdersRecord);

}
