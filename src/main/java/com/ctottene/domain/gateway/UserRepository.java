package com.ctottene.domain.gateway;

import com.ctottene.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
