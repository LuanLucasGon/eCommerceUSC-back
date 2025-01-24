package com.example.eCommerceUSC.auth.impl;

import com.example.eCommerceUSC.auth.AuthController;
import com.example.eCommerceUSC.config.security.TokenService;
import com.example.eCommerceUSC.auth.application.dto.LoginRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.RegisterRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.ResponseDTO;
import com.example.eCommerceUSC.user.infrastructure.UserRepositoryService;
import com.example.eCommerceUSC.user.infrastructure.repositories.UserRepository;
import com.example.eCommerceUSC.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
        User user = this.userRepositoryService.findUserByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.userRepositoryService.findUserByEmail(body.email());
        System.out.println(user);;
        if(user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setCpf(body.cpf());
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.userRepositoryService.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok().body(new ResponseDTO(newUser.getName(), token));

        }
        return ResponseEntity.badRequest().build();
    }
}
