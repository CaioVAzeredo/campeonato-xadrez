package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.Professor;

import java.time.LocalDateTime;

public record DadosAtualizarTurma(

        Professor professor,

        Long idProfessor,

        String nome,

        String descricao,

        Integer ano_letivo,

        boolean ativo
) {
}
