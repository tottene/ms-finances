package com.ctottene.application.usecase.impl;

import com.ctottene.application.usecase.UpdateCategoryUseCase;
import com.ctottene.application.usecase.dto.UpdateCategoryInput;
import com.ctottene.domain.gateway.CategoryRepository;
import com.ctottene.domain.model.Category;
import com.ctottene.infrastructure.security.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final Logger log = LoggerFactory.getLogger(UpdateCategoryUseCaseImpl.class);

    private final CategoryRepository repository;
    private final AuthenticatedUser authenticatedUser;

    public UpdateCategoryUseCaseImpl(CategoryRepository repository, AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public Void execute(UpdateCategoryInput input) {
        Category category = repository.findById(input.id())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setName(input.name());
        category.setDescription(input.description());
        category.setUpdatedAt(Instant.now());
        category.setUpdatedBy(authenticatedUser.getId());

        log.info("Updating category {}", category.getId());
        repository.save(category);
        return null;
    }
}
