package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.Professor;

import java.time.LocalDateTime;

public record DadosListagemTurma(
        Long id,
        Professor professor,
        String nome,
        String descricao,
        Integer ano_letivo,
        LocalDateTime data_criacao,
        LocalDateTime data_atualizacao
) {
    public DadosListagemTurma(Turma turma) {
        this(
                turma.getId_turma(),
                turma.getProfessor(),
                turma.getNome(),
                turma.getDescricao(),
                turma.getAno_letivo(),
                turma.getData_criacao(),
                turma.getData_atualizacao()
        );
    }
}