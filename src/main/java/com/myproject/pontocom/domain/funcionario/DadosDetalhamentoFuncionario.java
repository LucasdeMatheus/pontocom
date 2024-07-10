package com.myproject.pontocom.domain.funcionario;

import com.myproject.pontocom.domain.endereco.Endereco;

import java.time.LocalDate;

public record DadosDetalhamentoFuncionario(String email,
                                           String telefone,
                                           String cargo,
                                           LocalDate dataDeAdmissao,
                                           String nome,
                                           LocalDate dataDeNascimento,
                                           String cidade,
                                           String cep,
                                           String numero
) {
    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(
                funcionario.getContato().getEmail(),
                funcionario.getContato().getTelefone(),
                funcionario.getDadosProficionais().getCargo().toString(),
                funcionario.getDadosProficionais().getDataDeAdmissao(),
                funcionario.getNome(),
                funcionario.getDataDeNascimento(),
                funcionario.getEndereco().getCep(),
                funcionario.getEndereco().getCidade(),
                funcionario.getEndereco().getNumero()
        );
    }


}
