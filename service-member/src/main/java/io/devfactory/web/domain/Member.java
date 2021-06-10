package io.devfactory.web.domain;

import io.devfactory.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "tb_member")
@Entity
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50, unique = true)
  private String userId;

  @Column(nullable = false, length = 50, unique = true)
  private String email;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 100)
  private String password;

  @Builder
  private Member(String userId, String email, String name, String password) {
    this.userId = userId;
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public void updatePassword(String encryptedPassword) {
    this.password = encryptedPassword;
  }

  public void updateUserId(String userId) {
    this.userId = userId;
  }

}
