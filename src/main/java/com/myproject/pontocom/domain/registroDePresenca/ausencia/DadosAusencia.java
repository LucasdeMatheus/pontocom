package com.myproject.pontocom.domain.registroDePresenca.ausencia;

import jakarta.validation.constraints.NotNull;

public record DadosAusencia (
        long idFuncionario,
        @NotNull
        MotivoAusencia motivo
){
}
