package com.ctottene.application.usecase;

public interface UseCaseInputOnly<I> {
    void execute(I input);
}