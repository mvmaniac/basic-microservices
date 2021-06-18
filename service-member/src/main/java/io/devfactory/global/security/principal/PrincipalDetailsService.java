package io.devfactory.global.security.principal;

import io.devfactory.web.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Component
public class PrincipalDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  public PrincipalDetailsService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final var findMember = memberRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found..."));
    return new PrincipalDetails(findMember);
  }

}
