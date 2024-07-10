package com.myproject.pontocom.domain.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosContato(
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O email deve ser válido")
        String email,

        @NotBlank(message = "O telefone não pode estar em branco")
        String telefone
) {}