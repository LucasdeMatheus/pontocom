package com.myproject.pontocom.domain.registroDePresenca.ausencia;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

public class Presente {
    private Boolean funcionarioPresente;

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

    private MotivoAusencia motivoAusencia;
}
