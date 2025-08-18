package com.example.camp_xadrez.api.domain.professor;

public record DadosListagemProfessor(
        Long id,
        String nome,
        String email,
        java.time.LocalDateTime dataCriacao,
        java.time.LocalDateTime ultimaAtualizacao) {
    public DadosListagemProfessor(Professor professor){
        this(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getData_criacao(),
                professor.getUltima_atualizacao()
        );
    }
}