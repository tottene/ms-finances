package com.ctottene.infrastructure.persistence.repository;

import com.ctottene.domain.gateway.CategoryRepository;
import com.ctottene.domain.model.Category;
import com.ctottene.infrastructure.persistence.JpaCategoryEntityRepository;
import com.ctottene.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryEntityRepository jpaRepository;

    public CategoryRepositoryImpl(JpaCategoryEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return jpaRepository.findById(id).map(CategoryEntity::toModel);
    }

    @Override
    public List<Category> findAllByTenantId(UUID tenantId) {
        return jpaRepository.findAllByTenantId(tenantId).stream()
                .map(CategoryEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = CategoryEntity.fromModel(category);
        CategoryEntity saved = jpaRepository.save(entity);
        return saved.toModel();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
