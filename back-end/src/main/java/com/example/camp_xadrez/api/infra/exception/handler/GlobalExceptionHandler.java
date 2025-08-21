package com.example.camp_xadrez.api.infra.exception.handler;

import com.example.camp_xadrez.api.infra.exception.base.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    private static final String CORRELATION_HEADER = "X-Correlation-Id";
//
//    @ExceptionHandler(ApiException.class)
//    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, HttpServletRequest req) {
//        ErrorResponse body = buildResponse(
//                ex.getHttpStatus(),
//                ex.getProblemCode(),
//                ex.getMessage(),
//                req.getRequestURI(),
//                null
//        );
//        return ResponseEntity.status(ex.getHttpStatus().body(body));
//    }
//

}
