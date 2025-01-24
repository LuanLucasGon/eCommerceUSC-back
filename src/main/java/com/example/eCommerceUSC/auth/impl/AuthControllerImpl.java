package com.example.eCommerceUSC.auth.impl;

import com.example.eCommerceUSC.auth.AuthController;
import com.example.eCommerceUSC.auth.application.useCases.LoginUser;
import com.example.eCommerceUSC.auth.application.useCases.RegisterNewUser;
import com.example.eCommerceUSC.config.security.TokenService;
import com.example.eCommerceUSC.auth.application.dto.LoginRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.RegisterRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.ResponseDTO;
import com.example.eCommerceUSC.user.infrastructure.UserRepositoryService;
import com.example.eCommerceUSC.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        return LoginUser.execute(body, userRepositoryService, passwordEncoder, tokenService);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
         return RegisterNewUser.execute(body, userRepositoryService, passwordEncoder, tokenService);
    }
}
