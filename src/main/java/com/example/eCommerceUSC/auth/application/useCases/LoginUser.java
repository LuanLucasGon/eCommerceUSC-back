package com.example.eCommerceUSC.auth.application.useCases;

import com.example.eCommerceUSC.auth.application.dto.LoginRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.RegisterRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.ResponseDTO;
import com.example.eCommerceUSC.config.security.TokenService;
import com.example.eCommerceUSC.user.domain.User;
import com.example.eCommerceUSC.user.infrastructure.UserRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class LoginUser {
    public static ResponseEntity execute(LoginRequestDTO body, UserRepositoryService repository, PasswordEncoder encoder, TokenService tokenService){
        User user = repository.findUserByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(encoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
