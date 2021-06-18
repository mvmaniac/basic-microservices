package io.devfactory.web.repository;

import io.devfactory.web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUniqueId(String uniqueId);

  Optional<Member> findByEmail(String email);

}

