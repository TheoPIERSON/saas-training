package com.saas_learning.backend.services;

import com.saas_learning.backend.entities.PasswordResetToken;
import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.repositories.PasswordResetTokenRepository;
import com.saas_learning.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NotificationService emailService; // Service pour envoyer des emails

    @Value("${app.password-reset.expiration-minutes:30}")
    private int expirationMinutes;

    @Transactional
    public String createPasswordResetTokenForUser(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (!userOpt.isPresent()) {
            return null; // Utilisateur non trouvé
        }

        User user = userOpt.get();

        // Supprimer les tokens existants pour cet utilisateur
        tokenRepository.findByUser(user).ifPresent(token -> tokenRepository.delete(token));

        // Créer un nouveau token
        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken(
                token,
                user,
                LocalDateTime.now().plusMinutes(expirationMinutes)
        );
        tokenRepository.save(myToken);

        // Envoyer l'email avec le lien de réinitialisation
        emailService.sendPasswordResetEmail(user.getEmail(), token);

        return token;
    }

    @Transactional
    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);

        if (!tokenOpt.isPresent()) {
            return false; // Token non trouvé
        }

        PasswordResetToken resetToken = tokenOpt.get();

        if (resetToken.isExpired()) {
            tokenRepository.delete(resetToken);
            return false; // Token expiré
        }

        return true; // Token valide
    }

    @Transactional
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);

        if (!tokenOpt.isPresent() || tokenOpt.get().isExpired()) {
            return false;
        }

        PasswordResetToken resetToken = tokenOpt.get();
        User user = resetToken.getUser();

        // Mettre à jour le mot de passe
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Supprimer le token utilisé
        tokenRepository.delete(resetToken);

        return true;
    }
}