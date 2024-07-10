package com.myproject.pontocom.domain.contato;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Contato {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void atualizarInformacoes(Contato contato) {
        if (contato.telefone != null){
            this.telefone = contato.telefone;
        }
        if (contato.email != null){
            this.email = contato.email;
        }
    }
}

