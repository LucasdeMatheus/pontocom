package com.myproject.pontocom.domain.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
    @NotBlank
    private String cidade;
    @NotBlank
    private String cep;
    private String numero;

    public Endereco(String numero, String cep, String cidade) {
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
    }
    public Endereco() {
        // Construtor padrão
    }

    public Endereco(Endereco endereco) {
    }

    public void atualizarInformacoes(Endereco endereco) {
        if (endereco.cep != null) {
            this.cep = endereco.cep;
        }
        if (endereco.cidade != null) {
            this.cidade = endereco.cidade;
        }
        if (endereco.numero != null) {
            this.numero = endereco.numero;
        }
    }

    public @NotBlank String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank String cidade) {
        this.cidade = cidade;
    }

    public @NotBlank String getCep() {
        return cep;
    }

    public void setCep(@NotBlank String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
