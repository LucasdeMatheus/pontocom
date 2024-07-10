package com.myproject.pontocom.domain.funcionario;

import com.myproject.pontocom.domain.contato.Contato;
import com.myproject.pontocom.domain.endereco.Endereco;

public record DadosAtualizacaoFuncionario(
        long id,
        String nome,
        Endereco endereco,
        Contato contato
) {
}
