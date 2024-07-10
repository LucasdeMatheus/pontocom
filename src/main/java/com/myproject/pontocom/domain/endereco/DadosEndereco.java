package com.myproject.pontocom.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank(message = "A cidade não pode estar em branco")
        String cidade,

        @NotBlank(message = "O CEP não pode estar em branco")
        @Pattern(regexp = "\\d{8}", message = "Formato de CEP inválido")
        String cep,

        String numero
) {}

