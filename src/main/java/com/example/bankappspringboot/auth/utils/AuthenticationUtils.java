package com.example.bankappspringboot.auth.utils;

import com.example.bankappspringboot.auth.domain.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {

  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public UserEntity getCurrentUserInfo() {
    Authentication authentication = getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      return ((UserEntity) authentication.getPrincipal());
    }
    return null;
  }

}
