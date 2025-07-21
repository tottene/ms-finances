package com.ctottene.app.controller;

import com.ctottene.application.usecase.RegisterExpenseUseCase;
import com.ctottene.application.usecase.dto.RegisterExpenseInput;
import com.ctottene.application.usecase.dto.RegisterExpenseOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final RegisterExpenseUseCase registerExpenseUseCase;

    public ExpenseController(RegisterExpenseUseCase registerExpenseUseCase) {
        this.registerExpenseUseCase = registerExpenseUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RegisterExpenseOutput> register(@RequestBody RegisterExpenseInput input) {
        RegisterExpenseOutput output = registerExpenseUseCase.execute(input);
        return ResponseEntity.ok(output);
    }
}
