package com.task.courseplatform.auth;

import com.task.courseplatform.auth.dto.RegisterRequest;
import com.task.courseplatform.auth.dto.RegisterResponse;
import org.springframework.web.bind.annotation.*;

import com.task.courseplatform.auth.dto.LoginRequest;
import com.task.courseplatform.auth.dto.LoginResponse;
import com.task.courseplatform.auth.jwt.JwtUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        UserEntity user = authService.register(
                request.getEmail(),
                request.getPassword()
        );

        return new RegisterResponse(
                user.getId(),
                user.getEmail(),
                "User registered successfully"
        );
    }

// Login Endpoint
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new LoginResponse(
                token,
                request.getEmail(),
                JwtUtil.getExpirationTime()
        );
    }

}
