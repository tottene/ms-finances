package com.ctottene.infrastructure.persistence.repository;

import com.ctottene.domain.gateway.UserRepository;
import com.ctottene.domain.model.User;
import com.ctottene.infrastructure.persistence.entity.UserMetadataEntity;
import com.ctottene.infrastructure.persistence.JpaUserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserEntityRepository jpaRepo;

    public UserRepositoryImpl(JpaUserEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email)
                .map(this::toDomain);
    }

    private User toDomain(UserMetadataEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setRole(entity.getRole());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        user.setCreatedBy(entity.getCreatedBy());
        user.setUpdatedBy(entity.getUpdatedBy());
        user.setTenantId(entity.getTenantId());
        user.setUserTimeZone(entity.getUserTimeZone());
        return user;
    }
}