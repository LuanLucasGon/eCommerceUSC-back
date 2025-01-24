package com.example.eCommerceUSC.user.infrastructure.impl;

import com.example.eCommerceUSC.user.domain.User;
import com.example.eCommerceUSC.user.infrastructure.UserRepositoryService;
import com.example.eCommerceUSC.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }
}
