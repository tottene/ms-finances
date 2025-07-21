package com.ctottene.application.usecase.auth;

import com.ctottene.application.usecase.UseCase;
import com.ctottene.application.usecase.auth.dto.LoginInput;
import com.ctottene.application.usecase.auth.dto.LoginOutput;

public interface LoginUseCase extends UseCase<LoginInput, LoginOutput> {}