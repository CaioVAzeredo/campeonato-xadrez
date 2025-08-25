package com.example.camp_xadrez.api.infra.exception.base;

import com.example.camp_xadrez.api.infra.exception.model.ProblemCode;
import org.springframework.http.HttpStatus;

public class AlunoJaMatriculadoException extends ApiException{
    public AlunoJaMatriculadoException(String message, ProblemCode problemCode, HttpStatus httpStatus) {
        super(
                String.format("O aluno ja está matriculado na turma "),
                problemCode,
                httpStatus.CONFLICT
        );
    }
}
