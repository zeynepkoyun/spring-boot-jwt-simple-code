package com.example.bankappspringboot.auth.utils;

import com.example.bankappspringboot.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtils {

  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public Long getCurrentUserId() {
    Authentication authentication = getAuthentication();
    System.out.println(authentication + " geldi");
    if (authentication != null && authentication.isAuthenticated()) {
      return ((UserDetailsImpl) authentication.getPrincipal()).getUserId();
    }
    return null;
  }

}
