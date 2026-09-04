package com.baz.vehicle.rent.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baz.vehicle.rent.config.JwtUtil;
import com.baz.vehicle.rent.dpo.AuthResponse;
import com.baz.vehicle.rent.dpo.SigninRequest;
import com.baz.vehicle.rent.dpo.UserResponse;
import com.baz.vehicle.rent.entity.User;
import com.baz.vehicle.rent.services.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public UserResponse signUp(@RequestBody User user) {
        User createdUser = userService.signup(user);
        return new UserResponse(createdUser.getId(), createdUser.getName(), createdUser.getEmail());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {

        try {

                UserResponse user = userService.signin(
                    request.getEmail(),
                    request.getPassword());

                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(new AuthResponse(token, user));

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
}
