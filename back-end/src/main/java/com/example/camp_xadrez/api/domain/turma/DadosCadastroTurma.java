package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTurma(
        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        Integer ano_letivo,

        @NotNull
        Long idProfessor
) {
}
