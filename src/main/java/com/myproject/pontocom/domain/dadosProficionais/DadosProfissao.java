package com.myproject.pontocom.domain.dadosProficionais;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosProfissao(
        @NotNull
        String cargo,
        @NotNull
        LocalDate dataDeAdmissao
) {
}
