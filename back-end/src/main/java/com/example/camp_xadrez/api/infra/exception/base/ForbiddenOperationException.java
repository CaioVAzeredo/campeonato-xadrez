package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public class ForbiddenOperationException extends ApiException {
    public ForbiddenOperationException(String message) {
        super(message, ProblemCode.ACESSO_DANIFICADO, HttpStatus.FORBIDDEN);
    }
}
