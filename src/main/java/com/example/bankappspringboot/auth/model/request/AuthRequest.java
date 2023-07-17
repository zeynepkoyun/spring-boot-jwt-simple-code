package com.example.bankappspringboot.auth.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
  private String password;
  private String email;
}
