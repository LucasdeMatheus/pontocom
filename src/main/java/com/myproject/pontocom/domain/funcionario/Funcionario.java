package com.myproject.pontocom.domain.funcionario;

import com.myproject.pontocom.domain.contato.Contato;
import com.myproject.pontocom.domain.dadosProficionais.Profissao;
import com.myproject.pontocom.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "funcionarios")
@Entity(name = "Funcionarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;

    @Embedded
    Endereco endereco;
    @Embedded
    Contato contato;
    @Embedded
    Profissao dadosProficionais;

    public  Funcionario(DadosCadastroFuncionario dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataDeNascimento = dados.dataDeNascimento();
        this.endereco = dados.endereco();
        this.contato = dados.contato();
        this.dadosProficionais = dados.dadosProficionais();
    }

    public Funcionario(DadosFuncionario dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataDeNascimento = dados.dataDeNascimento();
        this.endereco = new Endereco(dados.endereco());
        this.contato = dados.contato();
        this.dadosProficionais = dados.dadosProficionais();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Profissao getDadosProficionais() {
        return dadosProficionais;
    }

    public void setDadosProficionais(Profissao dadosProficionais) {
        this.dadosProficionais = dadosProficionais;
    }

    public void atualizarInformacoes(DadosAtualizacaoFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.endereco() != null) {
            if (this.endereco == null) {
                this.endereco = dados.endereco();
            } else {
                this.endereco.atualizarInformacoes(dados.endereco());
            }
        }
        if (dados.contato() != null) {
            this.contato.atualizarInformacoes(dados.contato());
        }

    }
}
