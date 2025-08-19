package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.Professor;

import java.time.LocalDateTime;

public record DadosListagemTurma(
        Long id,
        String nome,
        String descricao,
        Integer ano_letivo,
        LocalDateTime data_criacao,
        LocalDateTime data_atualizacao,
        Long id_professor,
        String nome_professor

) {
    public DadosListagemTurma(Turma turma) {
        this(
                turma.getId_turma(),
                turma.getNome(),
                turma.getDescricao(),
                turma.getAno_letivo(),
                turma.getData_criacao(),
                turma.getUltima_atualizacao(),
                turma.getProfessor() != null ? turma.getProfessor().getId() : null,
                turma.getProfessor() != null ? turma.getProfessor().getNome() : null
        );
    }
}