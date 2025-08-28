package com.example.prova.controller;

import com.example.prova.dto.LoginRequest;
import com.example.prova.dto.TokenResponse;
import com.example.prova.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req) {
        var auth = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        var result = authManager.authenticate(auth);
        var token = jwtService.generateToken((org.springframework.security.core.userdetails.UserDetails) result.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}