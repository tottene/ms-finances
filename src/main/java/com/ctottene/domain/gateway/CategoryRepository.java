package com.ctottene.domain.gateway;

import com.ctottene.domain.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> findById(UUID id);
    List<Category> findAllByTenantId(UUID tenantId);
    Category save(Category category);
    void deleteById(UUID id);
}
