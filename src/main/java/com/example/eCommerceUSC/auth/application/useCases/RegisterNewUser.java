package com.example.eCommerceUSC.auth.application.useCases;

import com.example.eCommerceUSC.auth.application.dto.RegisterRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.ResponseDTO;
import com.example.eCommerceUSC.config.security.TokenService;
import com.example.eCommerceUSC.user.domain.User;
import com.example.eCommerceUSC.user.infrastructure.UserRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class RegisterNewUser {

    public static ResponseEntity execute(RegisterRequestDTO body, UserRepositoryService repository, PasswordEncoder encoder, TokenService tokenService){
        Optional<User> user = repository.findUserByEmail(body.email());
        if(user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(encoder.encode(body.password()));
            newUser.setCpf(body.cpf());
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            repository.save(newUser);
            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok().body(new ResponseDTO(newUser.getName(), token));

        }
        return ResponseEntity.badRequest().build();
    }
}
