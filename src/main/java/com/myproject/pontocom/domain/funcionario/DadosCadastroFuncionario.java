package com.myproject.pontocom.domain.funcionario;

import com.myproject.pontocom.domain.contato.Contato;
import com.myproject.pontocom.domain.dadosProficionais.Profissao;
import com.myproject.pontocom.domain.endereco.DadosEndereco;
import com.myproject.pontocom.domain.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroFuncionario (
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotNull
        LocalDate dataDeNascimento,
        Endereco endereco,
        Contato contato,
        Profissao dadosProficionais
) {
}
