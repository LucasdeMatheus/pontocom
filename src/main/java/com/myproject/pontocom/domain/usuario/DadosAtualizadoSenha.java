package com.myproject.pontocom.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizadoSenha(
        @NotBlank
        String senha
) {
}
