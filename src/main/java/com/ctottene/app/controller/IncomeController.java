package com.ctottene.app.controller;

import com.ctottene.application.usecase.RegisterIncomeUseCase;
import com.ctottene.application.usecase.dto.RegisterIncomeInput;
import com.ctottene.application.usecase.dto.RegisterIncomeOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private final RegisterIncomeUseCase registerIncomeUseCase;

    public IncomeController(RegisterIncomeUseCase registerIncomeUseCase) {
        this.registerIncomeUseCase = registerIncomeUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RegisterIncomeOutput> register(@RequestBody RegisterIncomeInput input) {
        RegisterIncomeOutput output = registerIncomeUseCase.execute(input);
        return ResponseEntity.ok(output);
    }
}
