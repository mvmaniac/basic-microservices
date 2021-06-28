package io.devfactory.web.service;

import feign.FeignException;
import io.devfactory.error.ServiceRuntimeException;
import io.devfactory.web.client.OrderServiceClient;
import io.devfactory.web.domain.Member;
import io.devfactory.web.dto.record.MemberAndOrdersRecord;
import io.devfactory.web.dto.record.OrderRecord;
import io.devfactory.web.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Slf4j
@Transactional(readOnly = true)
@Service
public class MemberService {

  private final Environment environment;
  private final RestTemplate restTemplate;
  private final OrderServiceClient orderServiceClient;

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  public MemberService(Environment environment, RestTemplate restTemplate,
      OrderServiceClient orderServiceClient, PasswordEncoder passwordEncoder,
      MemberRepository memberRepository) {
    this.environment = environment;
    this.restTemplate = restTemplate;
    this.orderServiceClient = orderServiceClient;
    this.passwordEncoder = passwordEncoder;
    this.memberRepository = memberRepository;
  }

  @Transactional
  public Member saveMember(Member member) {
    member.updateUniqueId(UUID.randomUUID().toString());
    member.encryptPassword(passwordEncoder.encode(member.getPassword()));
    return memberRepository.save(member);
  }

  public Member findMember(String uniqueId) {
    return memberRepository.findByUniqueId(uniqueId)
        .orElseThrow(() -> new ServiceRuntimeException("Member not found."));
  }

  public MemberAndOrdersRecord findMemberWithOrders(String uniqueId) {
    final var findMember = findMember(uniqueId);

    // RestTemplate 사용
//    final var orderUrl = String.format(Objects.toString(environment.getProperty("service-order.url"), ""), uniqueId);
//    final var orderResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null,
//        new ParameterizedTypeReference<List<OrderRecord>>() {
//        });
//    final var orders = orderResponse.getBody();

    // FeignClient 사용
    List<OrderRecord> orders = emptyList();

    try {
      // order 에서 예외가 발생하더라도 회원정보는 내주기 위해 예외처리 함
      // 만약 order 정보도 필수로 나와야 한다면 예외처리를 제거하고 FeignErrorDecoder 처리
      orders = orderServiceClient.retrieveOrders(uniqueId);
    } catch (FeignException e) {
      log.error("MemberService.findMemberWithOrders.FeignException: {}", e.getMessage(), e);
    }

    return new MemberAndOrdersRecord(findMember, orders);
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

}
