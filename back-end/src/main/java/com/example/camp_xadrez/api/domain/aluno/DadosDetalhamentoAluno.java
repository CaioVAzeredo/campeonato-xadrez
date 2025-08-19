package com.example.camp_xadrez.api.domain.aluno;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        Integer pontos_total
) {
    public DadosDetalhamentoAluno(Aluno aluno){
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getPontos_total()
        );
    }
}
