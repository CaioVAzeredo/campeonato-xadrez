package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.DadosDetalhamentoProfessor;
import com.example.camp_xadrez.api.domain.professor.Professor;

public record DadosDetalhamentoTurma(
        Long id,
        String nome,
        String descricao,
        Integer ano_letivo,
        Long id_professor,
        String nome_professor
) {
    public DadosDetalhamentoTurma(Turma turma){
        this(
                turma.getId_turma(),
                turma.getNome(),
                turma.getDescricao(),
                turma.getAno_letivo(),
                turma.getProfessor().getId(),
                turma.getProfessor().getNome()
        );
    }
}
