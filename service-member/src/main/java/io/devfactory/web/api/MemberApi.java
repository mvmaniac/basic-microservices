package io.devfactory.web.api;

import io.devfactory.web.dto.MemberMapper;
import io.devfactory.web.dto.request.MemberRequestView;
import io.devfactory.web.dto.response.MemberResponseView;
import io.devfactory.web.dto.response.MemberWithOrdersResponseView;
import io.devfactory.web.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/members")
@RestController
public class MemberApi {

  private final MemberService memberService;
  private final MemberMapper memberMapper;

  public MemberApi(MemberService memberService, MemberMapper memberMapper) {
    this.memberService = memberService;
    this.memberMapper = memberMapper;
  }

  @PostMapping
  public ResponseEntity<MemberResponseView> createMember(
      @RequestBody MemberRequestView requestView) {
    final var memberRecord = memberService.saveMember(memberMapper.requestViewOf(requestView));
    return ResponseEntity.ok(memberMapper.toResponseView(memberRecord));
  }

  @GetMapping("/{uniqueId}")
  public ResponseEntity<MemberWithOrdersResponseView> retrieveMember(
      @PathVariable("uniqueId") String uniqueId) {
    final var memberAndOrdersRecord = memberService.findMemberWithOrders(uniqueId);
    return ResponseEntity.ok(memberMapper.recordToView(memberAndOrdersRecord));
  }

  @GetMapping
  public ResponseEntity<List<MemberResponseView>> retrieveMembers() {
    return ResponseEntity.ok(memberMapper.toResponseViews(memberService.findMembers()));
  }

}
