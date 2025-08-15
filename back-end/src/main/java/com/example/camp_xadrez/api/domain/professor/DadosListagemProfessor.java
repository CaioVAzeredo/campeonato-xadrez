package com.example.camp_xadrez.api.domain.professor;

public record DadosListagemProfessor(
        Long id,
        String nome,
        String email
) {
    public DadosListagemProfessor(Professor professor){
        this(
                professor.getId(),
                professor.getNome(),
                professor.getEmail()
        );
    }
}
