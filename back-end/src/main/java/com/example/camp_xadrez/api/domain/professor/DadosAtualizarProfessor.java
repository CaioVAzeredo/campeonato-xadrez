package com.example.camp_xadrez.api.domain.professor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProfessor(
        @NotNull
        Long id,
        String nome,
        String email,
        boolean ativo
) {
}
