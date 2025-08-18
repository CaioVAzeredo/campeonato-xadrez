package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.DadosDetalhamentoProfessor;
import com.example.camp_xadrez.api.domain.professor.Professor;

public record DadosDetalhamentoTurma(
        Long id,
        Professor professor,
        String nome,
        String descricao,
        Integer ano_letivo
) {
    public DadosDetalhamentoTurma(Turma turma){
        this(
                turma.getId_turma(),
                turma.getProfessor(),
                turma.getNome(),
                turma.getDescricao(),
                turma.getAno_letivo()
        );
    }
}
