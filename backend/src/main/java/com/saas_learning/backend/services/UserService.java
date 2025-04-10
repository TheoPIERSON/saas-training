package com.saas_learning.backend.services;

import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private static boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return Pattern.matches(regex, email);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    @Transactional
    public User addUser(User user) {
        if (!isEmailValid(user.getEmail())) {
            throw new RuntimeException("Votre email n'est pas valide");
        }
        Optional<User> usersOptional = userRepository.findByEmail(user.getEmail());
        if (usersOptional.isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        user.setPassword("0000");
        user.setPremium(false);
        return userRepository.save(user);
    }
}
