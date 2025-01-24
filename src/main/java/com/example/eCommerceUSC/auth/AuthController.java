package com.example.eCommerceUSC.auth;

import com.example.eCommerceUSC.auth.application.dto.LoginRequestDTO;
import com.example.eCommerceUSC.auth.application.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity login(LoginRequestDTO body);

    ResponseEntity register(RegisterRequestDTO body);

}
