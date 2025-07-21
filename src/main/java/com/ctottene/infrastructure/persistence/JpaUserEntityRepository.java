package com.ctottene.infrastructure.persistence;

import com.ctottene.infrastructure.persistence.entity.UserMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserEntityRepository extends JpaRepository<UserMetadataEntity, UUID> {
    Optional<UserMetadataEntity> findByEmail(String email);
}
