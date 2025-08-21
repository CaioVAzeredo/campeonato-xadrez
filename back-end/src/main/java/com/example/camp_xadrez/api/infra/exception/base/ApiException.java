package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException{
    private final ProblemCode problemCode;

    private final HttpStatus httpStatus;

    public ApiException(String message, ProblemCode problemCode, HttpStatus httpStatus) {
        super(message);
        this.problemCode = problemCode;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ProblemCode getProblemCode() {
        return problemCode;
    }
}