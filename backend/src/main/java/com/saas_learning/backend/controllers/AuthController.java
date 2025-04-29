package com.saas_learning.backend.controllers;

import com.saas_learning.backend.config.security.JwtUtil;
import com.saas_learning.backend.dto.JwtResponse;
import com.saas_learning.backend.dto.LoginRequest;
import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            // Vous pouvez ajouter le token à une liste noire ou l'invalider d'une autre manière
            // jwtUtil.invalidateToken(jwt);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

