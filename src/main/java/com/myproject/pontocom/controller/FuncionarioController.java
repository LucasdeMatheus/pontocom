package com.myproject.pontocom.controller;

import com.myproject.pontocom.domain.funcionario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionarios")
@SecurityRequirement(name = "bearer-key")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping("/{email}")
    public ResponseEntity dadosFuncionario(@PathVariable String email){
        var dados = repository.findByContatoEmail(email);
        return ResponseEntity.ok(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionarios>> listar(Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemFuncionarios::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder){
        var funcionario = new Funcionario(dados);
        repository.save(funcionario);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();

        return  ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados){
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }
}

