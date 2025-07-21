package com.ctottene.application.usecase.category.impl;

import com.ctottene.application.usecase.category.DeleteCategoryUseCase;
import com.ctottene.domain.gateway.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryRepository repository;

    public DeleteCategoryUseCaseImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID input) {
        repository.deleteById(input);
    }
}
