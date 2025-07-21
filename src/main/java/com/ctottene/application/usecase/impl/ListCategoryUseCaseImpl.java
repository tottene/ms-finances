package com.ctottene.application.usecase.impl;

import com.ctottene.application.usecase.ListCategoryUseCase;
import com.ctottene.application.usecase.dto.CategoryOutput;
import com.ctottene.domain.gateway.CategoryRepository;
import com.ctottene.infrastructure.security.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListCategoryUseCaseImpl implements ListCategoryUseCase {

    private final CategoryRepository repository;
    private final AuthenticatedUser authenticatedUser;

    public ListCategoryUseCaseImpl(CategoryRepository repository, AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public List<CategoryOutput> execute() {
        return repository.findAllByTenantId(authenticatedUser.getTenantId())
                .stream()
                .map(c -> new CategoryOutput(c.getId(), c.getName(), c.getDescription()))
                .collect(Collectors.toList());
    }
}
