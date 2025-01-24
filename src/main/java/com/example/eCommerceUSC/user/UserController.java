package com.example.eCommerceUSC.user;

import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<String> getUser();
}
