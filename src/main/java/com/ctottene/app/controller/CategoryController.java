package com.ctottene.app.controller;

import com.ctottene.application.usecase.category.DeleteCategoryUseCase;
import com.ctottene.application.usecase.category.ListCategoryUseCase;
import com.ctottene.application.usecase.category.RegisterCategoryUseCase;
import com.ctottene.application.usecase.category.UpdateCategoryUseCase;
import com.ctottene.application.usecase.category.dto.CategoryOutput;
import com.ctottene.application.usecase.category.dto.RegisterCategoryInput;
import com.ctottene.application.usecase.category.dto.RegisterCategoryOutput;
import com.ctottene.application.usecase.category.dto.UpdateCategoryInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final RegisterCategoryUseCase registerUseCase;
    private final ListCategoryUseCase listUseCase;
    private final UpdateCategoryUseCase updateUseCase;
    private final DeleteCategoryUseCase deleteUseCase;

    public CategoryController(
            RegisterCategoryUseCase registerUseCase,
            ListCategoryUseCase listUseCase,
            UpdateCategoryUseCase updateUseCase,
            DeleteCategoryUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RegisterCategoryOutput> register(@RequestBody RegisterCategoryInput input) {
        RegisterCategoryOutput output = registerUseCase.execute(input);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<CategoryOutput>> list() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateCategoryInput input) {
        updateUseCase.execute(new UpdateCategoryInput(id, input.name(), input.description()));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
