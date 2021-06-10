package io.devfactory.web.dto;

import io.devfactory.web.domain.Member;
import io.devfactory.web.dto.request.MemberRequestView;
import io.devfactory.web.dto.response.MemberResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  MemberDto requestViewToDto(MemberRequestView requestView);
  MemberResponseView dtoToResponseView(MemberDto dto);

  Member dtoOf(MemberDto dto);
  MemberDto toDto(Member member);

}
