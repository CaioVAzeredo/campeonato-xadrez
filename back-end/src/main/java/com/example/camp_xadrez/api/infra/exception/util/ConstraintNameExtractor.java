package com.example.camp_xadrez.api.infra.exception.util;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

public class ConstraintNameExtractor {

    private static final Map<String, String> CONSTRAINT_MESSAGES = new HashMap<>();

    static {
        // exemplo de UNIQUE
        CONSTRAINT_MESSAGES.put("uk_aluno_email", "Já existe um aluno cadastrado com este e-mail.");
        CONSTRAINT_MESSAGES.put("uk_turma_nome", "Já existe uma turma com este nome.");

        // exemplo de FOREIGN KEY
        CONSTRAINT_MESSAGES.put("fk_turma_professor", "Professor informado não existe.");
        CONSTRAINT_MESSAGES.put("fk_aluno_turma", "Turma informada não existe.");

        // exemplo de CHECK
        CONSTRAINT_MESSAGES.put("chk_pontos_nao_negativos", "Os pontos do aluno não podem ser negativos.");
    }


    public static String extractMessage(Throwable ex) {
        String constraintName = extractConstraintName(ex);
        if (constraintName != null && CONSTRAINT_MESSAGES.containsKey(constraintName)) {
            return CONSTRAINT_MESSAGES.get(constraintName);
        }
        return "Violação de integridade de dados.";
    }


    private static String extractConstraintName(Throwable ex) {
        if (ex instanceof ConstraintViolationException cve) {
            return cve.getConstraintName();
        }

        if (ex instanceof DataIntegrityViolationException dive && dive.getCause() != null) {
            return extractConstraintName(dive.getCause());
        }

        if (ex.getCause() != null) {
            return extractConstraintName(ex.getCause());
        }

        return null;
    }
}
