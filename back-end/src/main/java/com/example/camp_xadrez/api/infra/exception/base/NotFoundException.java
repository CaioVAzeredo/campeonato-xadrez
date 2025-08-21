package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException{

    public NotFoundException(String message) {
        super(message, ProblemCode.ALUNO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message, ProblemCode problemCode) {
        super(message, problemCode, HttpStatus.NOT_FOUND);
    }
}