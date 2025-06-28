package com.dev.issue_tracker.controller;

import com.dev.issue_tracker.dto.AuthResponseDTO;
import com.dev.issue_tracker.dto.LoginRequestDTO;
import com.dev.issue_tracker.dto.SignupRequestDTO;
import com.dev.issue_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@Valid @RequestBody SignupRequestDTO request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

