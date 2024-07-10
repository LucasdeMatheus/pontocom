package com.myproject.pontocom.domain.funcionario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosListagemFuncionarios(
        String email,
        String telefone,
        String cargo,
        LocalDate dataDeAdmissao,
        String nome,
        LocalDate dataDeNascimento
) {
    public DadosListagemFuncionarios(Funcionario funcionario) {
        this(
                funcionario.getContato().getEmail(),
                funcionario.getContato().getTelefone(),
                funcionario.getDadosProficionais().getCargo().toString(),
                funcionario.getDadosProficionais().getDataDeAdmissao(),
                funcionario.getNome(),
                funcionario.getDataDeNascimento()
        );
    }
}
