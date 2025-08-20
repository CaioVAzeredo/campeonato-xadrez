package com.example.camp_xadrez.api.domain.professor;


public record DadosAtualizarProfessor(
        String nome,
        String email,
        boolean ativo,
        String senha_hash
) {
}
