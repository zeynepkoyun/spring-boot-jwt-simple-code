package com.example.bankappspringboot.auth.service;

import com.example.bankappspringboot.auth.domain.UserEntity;
import com.example.bankappspringboot.auth.model.request.AuthRequest;
import com.example.bankappspringboot.auth.model.response.AuthResponse;
import com.example.bankappspringboot.auth.model.response.TestAuthResponse;
import com.example.bankappspringboot.auth.repository.UserRepository;
import com.example.bankappspringboot.auth.utils.AuthenticationUtils;
import com.example.bankappspringboot.security.JwtUtilities;
import com.example.bankappspringboot.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
  private UserRepository userRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private AuthenticationManager authenticationManager;
  private JwtUtilities jwtUtilities;
  private final AuthenticationUtils authenticationUtils;

  @Override
  public ResponseEntity<UserEntity> registerUser(UserEntity userEntity) {
    Optional<UserEntity> userInfo= userRepository.findByEmail(userEntity.getUsername());
    if(userInfo.isPresent()){
      throw new RuntimeException("Email already exists in database");
    }else{
      userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
      UserEntity savedUser = userRepository.save(userEntity);
      return ResponseEntity.ok(savedUser);
    }
  }

  @Override
  public ResponseEntity<AuthResponse> loginUser(AuthRequest authRequest) {
    Authentication authentication= authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(),
            authRequest.getPassword()
        )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String token = jwtUtilities.generateToken(userDetails.getEmail());
    return ResponseEntity.ok(AuthResponse.builder().token(token).build());
  }

  @Override
  public ResponseEntity<String> testLoggedUserName(Long userId) {
    System.out.println("userId geldi");
    TestAuthResponse testAuthResponse = TestAuthResponse.builder().username(String.valueOf(userId)).build();
    return ResponseEntity.ok(testAuthResponse.message());
  }
}
