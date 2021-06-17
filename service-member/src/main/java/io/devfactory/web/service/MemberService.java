package io.devfactory.web.service;

import io.devfactory.error.ServiceRuntimeException;
import io.devfactory.web.domain.Member;
import io.devfactory.web.dto.record.MemberAndOrdersRecord;
import io.devfactory.web.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Transactional(readOnly = true)
@Service
public class MemberService {

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
    this.passwordEncoder = passwordEncoder;
    this.memberRepository = memberRepository;
  }

  @Transactional
  public Member saveMember(Member member) {
    member.updateUniqueId(UUID.randomUUID().toString());
    member.encryptPassword(passwordEncoder.encode(member.getPassword()));
    return memberRepository.save(member);
  }

  public MemberAndOrdersRecord findMember(String uniqueId) {
    final var findMember = memberRepository.findByUniqueId(uniqueId)
        .orElseThrow(() -> new ServiceRuntimeException("Member not found."));
    return new MemberAndOrdersRecord(findMember, emptyList());
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

}
