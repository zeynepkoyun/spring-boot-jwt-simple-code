package com.example.bankappspringboot.auth.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "username", nullable = false)
  private String username; //UserDetails sınıfı içerisinde username olarak tanımlandığından veriyi alabilmek için name olarak düzenlendi.

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp                //CreationTimestamp anatosyonu ile default olarak ekleniyor.
  private LocalDateTime createdAt;

  }
