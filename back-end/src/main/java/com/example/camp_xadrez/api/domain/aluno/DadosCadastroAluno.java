package com.example.camp_xadrez.api.domain.aluno;

public record DadosCadastroAluno(
        String nome,
        Float pontos_total,
        Long id_turma
) {
}
