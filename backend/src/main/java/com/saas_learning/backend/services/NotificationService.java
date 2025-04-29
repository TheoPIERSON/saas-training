package com.saas_learning.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Value("${app.frontend-url:http://localhost:3000}")
    private String frontendUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Réinitialisation de votre mot de passe");

        String resetUrl = frontendUrl + "/reset-password?token=" + token;

        message.setText("Bonjour,\n\n" +
                "Vous avez demandé une réinitialisation de votre mot de passe.\n\n" +
                "Cliquez sur le lien suivant pour définir un nouveau mot de passe:\n" +
                resetUrl + "\n\n" +
                "Ce lien expirera dans 30 minutes.\n\n" +
                "Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet email.\n\n" +
                "Cordialement,\n" +
                "L'équipe de votre application.");

        javaMailSender.send(message);
    }
}
