package io.devfactory.web.api;

import io.devfactory.web.dto.MemberMapper;
import io.devfactory.web.dto.request.MemberRequestView;
import io.devfactory.web.dto.response.MemberResponseView;
import io.devfactory.web.dto.response.MemberWithOrdersResponseView;
import io.devfactory.web.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/service-member/members")
@RestController
public class MemberApi {

  private final MemberService memberService;

  public MemberApi(MemberService memberService) {
    this.memberService = memberService;
  }

  @PostMapping
  public ResponseEntity<MemberResponseView> createMember(
      @RequestBody MemberRequestView requestView) {
    final var memberRecord = memberService.saveMember(MemberMapper.INSTANCE.requestViewOf(requestView));
    return ResponseEntity.ok(MemberMapper.INSTANCE.toResponseView(memberRecord));
  }

  @GetMapping("/{uniqueId}")
  public ResponseEntity<MemberWithOrdersResponseView> retrieveMember(
      @PathVariable("uniqueId") String uniqueId) {
    final var memberAndOrdersRecord = memberService.findMember(uniqueId);
    return ResponseEntity.ok(MemberMapper.INSTANCE.recordToView(memberAndOrdersRecord));
  }

  @GetMapping
  public ResponseEntity<List<MemberResponseView>> retrieveMembers() {
    return ResponseEntity.ok(MemberMapper.INSTANCE.toResponseViews(memberService.findMembers()));
  }

}
