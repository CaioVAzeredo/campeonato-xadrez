package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public class DomainValidationException extends ApiException{
    public DomainValidationException(String message) {
        super(message, ProblemCode.ERRO_VALIDACAO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}