package com.dev.issue_tracker.service;

import com.dev.issue_tracker.config.JwtUtil;
import com.dev.issue_tracker.dto.AuthResponseDTO;
import com.dev.issue_tracker.dto.LoginRequestDTO;
import com.dev.issue_tracker.dto.SignupRequestDTO;
import com.dev.issue_tracker.model.Role;
import com.dev.issue_tracker.model.User;
import com.dev.issue_tracker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JwtUtil jwtUtil;

  public AuthResponseDTO signup(SignupRequestDTO request) {
    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);

    userRepository.save(user);

    String token = jwtUtil.generateToken(user);
    return new AuthResponseDTO(token);
  }

  public AuthResponseDTO login(@Valid LoginRequestDTO request) {
    User user =
        userRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }

    String token = jwtUtil.generateToken(user);
    return new AuthResponseDTO(token);
  }
}
