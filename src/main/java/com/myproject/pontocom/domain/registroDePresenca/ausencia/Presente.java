package com.myproject.pontocom.domain.registroDePresenca.ausencia;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Presente {
    private Boolean funcionarioPresente;

    @Enumerated(EnumType.STRING)
    private MotivoAusencia motivoAusencia;

    public Boolean getFuncionarioPresente() {
        return funcionarioPresente;
    }

    public void setFuncionarioPresente(Boolean funcionarioPresente) {
        this.funcionarioPresente = funcionarioPresente;
    }

    public MotivoAusencia getMotivoAusencia() {
        return motivoAusencia;
    }

    public void setMotivoAusencia(MotivoAusencia motivoAusencia) {
        this.motivoAusencia = motivoAusencia;
    }

    @Override
    public String toString() {
        return "Presente{" +
                "funcionarioPresente=" + funcionarioPresente +
                ", motivoAusencia=" + motivoAusencia +
                '}';
    }
}
