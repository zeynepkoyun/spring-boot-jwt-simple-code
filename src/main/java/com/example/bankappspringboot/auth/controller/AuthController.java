package com.example.bankappspringboot.auth.controller;

import com.example.bankappspringboot.auth.model.response.AuthResponse;
import com.example.bankappspringboot.auth.service.AuthService;
import com.example.bankappspringboot.auth.domain.UserEntity;
import com.example.bankappspringboot.auth.model.request.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
    return authService.registerUser(userEntity);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authenticationRequest) {
    return authService.loginUser(authenticationRequest);
  }

  @GetMapping("/test")
  public ResponseEntity<String> testMethod(Authentication authentication) {
    return authService.testLoggedUserName(authentication);
  }

}
