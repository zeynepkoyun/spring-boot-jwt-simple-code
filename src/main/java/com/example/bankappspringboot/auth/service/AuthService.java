package com.example.bankappspringboot.auth.service;

import com.example.bankappspringboot.auth.domain.UserEntity;
import com.example.bankappspringboot.auth.model.request.AuthRequest;
import com.example.bankappspringboot.auth.model.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface AuthService {

  ResponseEntity<UserEntity> registerUser(UserEntity userEntity);

  ResponseEntity<AuthResponse> loginUser(AuthRequest authRequest);

  ResponseEntity<String> testLoggedUserName(Authentication authentication);
}
