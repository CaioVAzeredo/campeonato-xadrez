package com.example.camp_xadrez.api.domain.turma;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTurma(
        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotBlank
        Integer ano_letivo,

        @NotBlank
        boolean ativo

) {
}
