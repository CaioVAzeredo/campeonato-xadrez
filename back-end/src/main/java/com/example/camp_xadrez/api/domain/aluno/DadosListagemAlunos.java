package com.example.camp_xadrez.api.domain.aluno;

import com.example.camp_xadrez.api.domain.turma.DadosListagemTurma;
import com.example.camp_xadrez.api.domain.turma.Turma;

import java.time.LocalDateTime;

public record DadosListagemAlunos(
        Long id,
        String nome,
        Float pontos_total,
        Long id_turma,
        String nome_turma,
        LocalDateTime data_criacao,
        LocalDateTime ultima_atualizacao
) {
    public DadosListagemAlunos(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getPontos_total(),
                aluno.getTurma().getId_turma(),
                aluno.getTurma().getNome(),
                aluno.getData_criacao(),
                aluno.getUltima_atualizacao()
        );
    }
}