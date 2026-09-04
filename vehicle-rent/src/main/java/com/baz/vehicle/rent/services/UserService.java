package com.baz.vehicle.rent.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baz.vehicle.rent.dpo.UserResponse;
import com.baz.vehicle.rent.entity.User;
import com.baz.vehicle.rent.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signup(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public UserResponse signin(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

}