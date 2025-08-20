package com.example.camp_xadrez.api.domain.aluno;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAluno(

        @NotBlank
        String nome,

        @NotBlank
        Float pontos_total,

        @NotNull
        Long id_turma
) {
}
