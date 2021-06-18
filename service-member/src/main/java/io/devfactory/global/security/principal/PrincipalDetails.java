package io.devfactory.global.security.principal;

import io.devfactory.web.domain.Member;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;

@Getter
public class PrincipalDetails extends User {

  private final transient Member member;

  public PrincipalDetails(Member member) {
    // TODO: 권한 처리?
    super(member.getEmail(), member.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    this.member = member;
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj)) {
      return false;
    }

    if (this == obj) return true;
    if (getClass() != obj.getClass()) return false;

    final var principalDetails = (PrincipalDetails) obj;
    return member.equals(principalDetails.getMember());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), member.hashCode());
  }

}
