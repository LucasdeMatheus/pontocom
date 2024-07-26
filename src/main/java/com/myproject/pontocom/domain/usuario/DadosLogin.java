package com.myproject.pontocom.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosLogin(@NotBlank
                         String login,
                         @NotBlank
                         String senha) {
}
