package com.example.camp_xadrez.api.domain.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProfessor(
        @NotBlank
        String nome,

        boolean ativo,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha_hash
) {
}
