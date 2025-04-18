package com.saas_learning.backend.controllers;

import com.saas_learning.backend.config.security.JwtUtil;
import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers (){
        List<User> users = (List<User>) userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extraire le token du header Authorization
            String token = authHeader.substring(7); // Enlever le préfixe "Bearer "

            // Récupérer l'email de l'utilisateur depuis le token JWT
            String userEmail = jwtUtil.getEmailFromToken(token);

            // Récupérer les détails de l'utilisateur depuis la base de données
            User user = userService.findByEmail(userEmail);

            // Masquer le mot de passe
            user.setPassword(null);

            System.out.println("Utilisateur trouvé: " + user.getFirstname() + " " + user.getLastname());

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.err.println("Erreur dans getCurrentUser: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'utilisateur: " + e.getMessage());
        }
    }
}
