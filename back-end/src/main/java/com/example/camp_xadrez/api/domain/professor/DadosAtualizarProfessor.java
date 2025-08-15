package com.example.camp_xadrez.api.domain.professor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProfessor(
        String nome,
        String email,
        boolean ativo,
        String senha_hash
) {
}
