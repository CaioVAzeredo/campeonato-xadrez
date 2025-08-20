package com.example.camp_xadrez.api.domain.aluno;

import com.example.camp_xadrez.api.domain.turma.DadosListagemTurma;
import com.example.camp_xadrez.api.domain.turma.Turma;

public record DadosListagemAlunos(
        Long id,
        String nome,
        Float pontos_total,
        Long id_turma,
        String nome_turma

) {
    public DadosListagemAlunos(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getPontos_total(),
                aluno.getTurma().getId_turma(),
                aluno.getTurma().getNome()
        );
    }
}
