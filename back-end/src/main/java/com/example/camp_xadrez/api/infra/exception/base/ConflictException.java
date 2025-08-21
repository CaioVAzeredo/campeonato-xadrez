package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

    public ConflictException(String message) {
        super(message, ProblemCode.CONFLITO, HttpStatus.CONFLICT);
    }
}
