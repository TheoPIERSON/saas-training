package com.saas_learning.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    private String password; // null si authentifié uniquement via Google

    private boolean isPremium;

    // Ajout pour suivre quand l'utilisateur est passé premium
    private LocalDateTime premiumSince;

    @Column(nullable = false)
    private boolean fromGoogle;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}