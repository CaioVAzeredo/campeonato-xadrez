package com.example.camp_xadrez.api.domain.professor;

public record DadosDetalhamentoProfessor(
        Long id,
        String nome,
        String email
) {
    public DadosDetalhamentoProfessor (Professor professor){
        this(
                professor.getId(),
                professor.getNome(),
                professor.getEmail()
        );
    }

}
