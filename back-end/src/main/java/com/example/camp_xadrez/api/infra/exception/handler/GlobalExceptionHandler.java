package com.example.camp_xadrez.api.infra.exception.handler;

import com.example.camp_xadrez.api.infra.exception.base.ApiException;
import com.example.camp_xadrez.api.infra.exception.model.ErrorDetail;
import com.example.camp_xadrez.api.infra.exception.model.ErrorResponse;
import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import com.example.camp_xadrez.api.infra.exception.util.ConstraintNameExtractor;
import com.example.camp_xadrez.api.infra.exception.util.CorrelationIdFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.PropertyValueException;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(basePackages = "com.example.camp_xadrez")
public class GlobalExceptionHandler {

    private static final String CORRELATION_HEADER = CorrelationIdFilter.HEADER;


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, HttpServletRequest req) {
        ErrorResponse body = buildResponse(
                ex.getHttpStatus(),
                ex.getProblemCode(),
                ex.getMessage(),
                req.getRequestURI(),
                null
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<ErrorDetail> details = new ArrayList<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            details.add(new ErrorDetail(
                    fe.getField(),
                    fe.getRejectedValue(),
                    fe.getDefaultMessage()
            ));
        }

        ErrorResponse body = buildResponse(
                HttpStatus.BAD_REQUEST,
                ProblemCode.ERRO_VALIDACAO,
                "Parâmetros inválidos na requisição.",
                req.getRequestURI(),
                details
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        List<ErrorDetail> details = List.of(
                new ErrorDetail(
                        ex.getName(),
                        ex.getValue(),
                        "Tipo inválido para o parâmetro. Esperado: " +
                                (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido")
                )
        );

        ErrorResponse body = buildResponse(
                HttpStatus.BAD_REQUEST,
                ProblemCode.ERRO_VALIDACAO,
                "Parâmetros inválidos na URL.",
                req.getRequestURI(),
                details
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(MissingServletRequestParameterException ex, HttpServletRequest req) {
        List<ErrorDetail> details = List.of(
                new ErrorDetail(ex.getParameterName(), null, "Parâmetro obrigatório ausente")
        );

        ErrorResponse body = buildResponse(
                HttpStatus.BAD_REQUEST,
                ProblemCode.ERRO_VALIDACAO,
                "Parâmetro obrigatório ausente.",
                req.getRequestURI(),
                details
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        ErrorResponse body = buildResponse(
                HttpStatus.BAD_REQUEST,
                ProblemCode.JSON_INVALIDO,
                "JSON do corpo da requisição está inválido ou mal formatado.",
                req.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandler(NoHandlerFoundException ex, HttpServletRequest req) {
        ErrorResponse body = buildResponse(
                HttpStatus.NOT_FOUND,
                ProblemCode.ROTA_NAO_ENCONTRADA,
                "Rota não encontrada.",
                req.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        ErrorResponse body = buildResponse(
                HttpStatus.METHOD_NOT_ALLOWED,
                ProblemCode.METODO_NAO_PERMITIDO,
                "Método HTTP não permitido para esta rota.",
                req.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        // Mensagem amigável vinda do extractor (FK/UK/CHECK, etc.)
        String friendly = ConstraintNameExtractor.extractMessage(ex);


        List<ErrorDetail> details = new ArrayList<>();
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof PropertyValueException pve) {
            details.add(new ErrorDetail(pve.getPropertyName(), null, "Valor obrigatório ausente"));
        }

        ErrorResponse body = buildResponse(
                HttpStatus.CONFLICT,
                ProblemCode.INTEGRIDADE_DADOS,
                friendly,
                req.getRequestURI(),
                details.isEmpty() ? null : details
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }


    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErrorResponse> handleSpringErrorResponse(ErrorResponseException ex,
                                                                   HttpServletRequest req) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ProblemCode code = status.is4xxClientError()
                ? ProblemCode.ERRO_VALIDACAO
                : ProblemCode.ERRO_INTERNO;


        String message;
        if (ex instanceof org.springframework.web.server.ResponseStatusException rse) {
            message = rse.getReason();
        } else {
            message = "Erro ao processar a requisição.";
        }

        ErrorResponse body = buildResponse(
                status,
                code,
                message,
                req.getRequestURI(),
                null
        );

        return ResponseEntity.status(status).body(body);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest req) {
        ErrorResponse body = buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ProblemCode.ERRO_INTERNO,
                "Ocorreu um erro inesperado. Se o problema persistir, contate o suporte.",
                req.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }


    private ErrorResponse buildResponse(HttpStatus status,
                                        ProblemCode code,
                                        String message,
                                        String path,
                                        List<ErrorDetail> details) {

        String correlationId = MDC.get(CorrelationIdFilter.MDC_KEY);
        ErrorResponse body = new ErrorResponse();
        body.setStatus(status.value());
        body.setError(status.getReasonPhrase());
        body.setCode(code.name());
        body.setMessage(message);
        body.setPath(path);
        body.setCorelationId(correlationId != null ? correlationId : "");
        body.setDetails(details);
        return body;
    }
}
