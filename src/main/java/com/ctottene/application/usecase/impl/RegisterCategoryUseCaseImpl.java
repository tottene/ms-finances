package com.ctottene.application.usecase.impl;

import com.ctottene.application.usecase.RegisterCategoryUseCase;
import com.ctottene.application.usecase.dto.RegisterCategoryInput;
import com.ctottene.application.usecase.dto.RegisterCategoryOutput;
import com.ctottene.domain.gateway.CategoryRepository;
import com.ctottene.domain.model.Category;
import com.ctottene.infrastructure.security.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RegisterCategoryUseCaseImpl implements RegisterCategoryUseCase {

    private final Logger log = LoggerFactory.getLogger(RegisterCategoryUseCaseImpl.class);

    private final CategoryRepository repository;
    private final AuthenticatedUser authenticatedUser;

    public RegisterCategoryUseCaseImpl(CategoryRepository repository, AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public RegisterCategoryOutput execute(RegisterCategoryInput input) {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName(input.name());
        category.setDescription(input.description());
        category.setCreatedAt(Instant.now());
        category.setUpdatedAt(Instant.now());
        category.setCreatedBy(authenticatedUser.getId());
        category.setTenantId(authenticatedUser.getTenantId());
        category.setUserTimeZone(authenticatedUser.getTimezone());

        log.info("Saving Category: {}", category.getName());
        Category saved = repository.save(category);
        log.info("Saved category {}", saved.getId());

        return new RegisterCategoryOutput(saved.getId());
    }
}
