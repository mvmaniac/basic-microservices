package io.devfactory.web.api;

import io.devfactory.web.dto.MemberMapper;
import io.devfactory.web.dto.request.MemberRequestView;
import io.devfactory.web.dto.response.MemberResponseView;
import io.devfactory.web.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RestController
public class MemberApi {

  private final MemberService memberService;

  public MemberApi(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping
  public ResponseEntity<MemberResponseView> createMember(
      @RequestBody MemberRequestView requestView) {
    final var memberDto = memberService.saveMember(MemberMapper.INSTANCE.requestViewToDto(requestView));
    return ResponseEntity.ok(MemberMapper.INSTANCE.dtoToResponseView(memberDto));
  }

}
