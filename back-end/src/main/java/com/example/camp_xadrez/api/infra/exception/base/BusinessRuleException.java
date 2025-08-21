package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public class BusinessRuleException extends ApiException {

    public BusinessRuleException(String message, ProblemCode problemCode, HttpStatus httpStatus) {
        super(message, problemCode.REGRA_NEGOCIO_VIOLADA, httpStatus.BAD_REQUEST);
    }
}
