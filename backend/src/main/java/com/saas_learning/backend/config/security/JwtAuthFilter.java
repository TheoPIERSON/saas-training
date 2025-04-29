package com.saas_learning.backend.config.security;

import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Bypass pour les routes publiques
        if (path.equals("/auth/login") || path.equals("/user/add-user")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            // Vérifier si le token est valide
            if (jwtUtil.validateToken(jwt)) {
                String email = jwtUtil.getEmailFromToken(jwt);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Optional<User> userOpt = userRepository.findByEmail(email);

                    if (userOpt.isPresent()) {
                        User user = userOpt.get();

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

