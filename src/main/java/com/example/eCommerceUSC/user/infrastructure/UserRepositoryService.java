package com.example.eCommerceUSC.user.infrastructure;

import com.example.eCommerceUSC.user.domain.User;

import java.util.Optional;

public interface UserRepositoryService {
    Optional<User> findUserByEmail(String email);

    void save(User user);
}
