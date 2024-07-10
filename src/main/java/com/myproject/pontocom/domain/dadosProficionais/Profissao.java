package com.myproject.pontocom.domain.dadosProficionais;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Profissao {
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    private LocalDate dataDeAdmissao;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(LocalDate dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }
}
