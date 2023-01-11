package io.devfactory.web.domain;

import io.devfactory.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
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
  private String uniqueId;

  @Column(nullable = false, length = 50, unique = true)
  private String email;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 100)
  private String password;

  @Builder
  private Member(String uniqueId, String email, String name, String password) {
    this.uniqueId = uniqueId;
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public void encryptPassword(String encryptedPassword) {
    this.password = encryptedPassword;
  }

  public void updateUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

}
