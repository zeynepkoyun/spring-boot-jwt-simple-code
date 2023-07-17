package com.example.bankappspringboot.auth.model.response;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class TestAuthResponse {
  private String username;

  public String message(){
    return String.format("%s kullanıcısı tarafından sayfa yüklendi.",username);
  }
}
