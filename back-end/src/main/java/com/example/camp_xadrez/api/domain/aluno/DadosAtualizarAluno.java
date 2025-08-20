package com.example.camp_xadrez.api.domain.aluno;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAluno(


        String nome,

        Float pontos_total,

        Long id_turma
) {
}
