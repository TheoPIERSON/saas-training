package com.saas_learning.backend.controllers;

import com.saas_learning.backend.dto.PasswordResetRequest;
import com.saas_learning.backend.dto.PasswordResetResponse;
import com.saas_learning.backend.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordResetRequest request) {
        String token = passwordResetService.createPasswordResetTokenForUser(request.getEmail());

        // Par sécurité, nous retournons toujours une réponse positive, même si l'email n'existe pas
        return ResponseEntity.ok(new PasswordResetResponse(
                "Si l'adresse email existe, un email de réinitialisation a été envoyé."
        ));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        boolean isValid = passwordResetService.validatePasswordResetToken(token);

        if (isValid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(new PasswordResetResponse(
                    "Le token de réinitialisation est invalide ou a expiré."
            ));
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
        boolean result = passwordResetService.resetPassword(
                request.getToken(),
                request.getPassword()
        );

        if (result) {
            return ResponseEntity.ok(new PasswordResetResponse(
                    "Votre mot de passe a été réinitialisé avec succès."
            ));
        } else {
            return ResponseEntity.badRequest().body(new PasswordResetResponse(
                    "Impossible de réinitialiser le mot de passe. Veuillez réessayer."
            ));
        }
    }
}