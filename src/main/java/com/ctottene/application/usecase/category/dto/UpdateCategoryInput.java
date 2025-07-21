package com.ctottene.application.usecase.category.dto;

import java.util.UUID;

public record UpdateCategoryInput(UUID id, String name, String description) {}
