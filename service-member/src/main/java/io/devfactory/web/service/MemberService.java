package io.devfactory.web.service;

import io.devfactory.web.dto.MemberDto;
import io.devfactory.web.dto.MemberMapper;
import io.devfactory.web.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
  public MemberDto saveMember(MemberDto memberDto) {
    final var member = MemberMapper.INSTANCE.dtoOf(memberDto);
    member.updateUserId(UUID.randomUUID().toString());
    member.updatePassword(passwordEncoder.encode(memberDto.getPassword()));

    final var savedMember = memberRepository.save(member);
    return MemberMapper.INSTANCE.toDto(savedMember);
  }

}
