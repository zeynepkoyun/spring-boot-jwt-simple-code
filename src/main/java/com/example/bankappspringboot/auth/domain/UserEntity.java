package com.example.bankappspringboot.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long user_id;

  @Column(name = "username", nullable = false)
  private String name; //UserDetails sınıfı içerisinde username olarak tanımlandığından veriyi alabilmek için name olarak düzenlendi.

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp                //CreationTimestamp anatosyonu ile default olarak ekleniyor.
  private LocalDateTime created_at;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    return authorities;
  }
  @Override
  public String getUsername() { //UserDetails sınıfı ile birlikte geliyor. Email bilgisini veriyoruz.
    return this.email;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
