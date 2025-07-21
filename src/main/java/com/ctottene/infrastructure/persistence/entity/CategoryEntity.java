package com.ctottene.infrastructure.persistence.entity;

import com.ctottene.domain.model.Category;
import com.ctottene.infrastructure.persistence.common.AuditMetadataEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import com.ctottene.infrastructure.persistence.entity.IncomeEntity;

import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoryEntity extends AuditMetadataEntity {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private java.util.List<IncomeEntity> incomes;

    public CategoryEntity() {}

    public static CategoryEntity fromModel(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.id = category.getId();
        entity.name = category.getName();
        entity.description = category.getDescription();
        entity.setCreatedAt(category.getCreatedAt());
        entity.setCreatedBy(category.getCreatedBy());
        entity.setUpdatedAt(category.getUpdatedAt());
        entity.setUpdatedBy(category.getUpdatedBy());
        entity.setTenantId(category.getTenantId());
        entity.setUserTimeZone(category.getUserTimeZone());
        return entity;
    }

    public Category toModel() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        category.setCreatedAt(getCreatedAt());
        category.setCreatedBy(getCreatedBy());
        category.setUpdatedAt(getUpdatedAt());
        category.setUpdatedBy(getUpdatedBy());
        category.setTenantId(getTenantId());
        category.setUserTimeZone(getUserTimeZone());
        if (incomes != null) {
            java.util.List<com.ctottene.domain.model.Income> list = new java.util.ArrayList<>();
            for (IncomeEntity incomeEntity : incomes) {
                list.add(incomeEntity.toModel());
            }
            category.setIncomes(list);
        }
        return category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.List<IncomeEntity> getIncomes() {
        return incomes;
    }

    public void setIncomes(java.util.List<IncomeEntity> incomes) {
        this.incomes = incomes;
    }
}
