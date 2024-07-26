package com.myproject.pontocom.controller;

import com.myproject.pontocom.domain.funcionario.*;
import com.myproject.pontocom.domain.usuario.DadosAtualizadoSenha;
import com.myproject.pontocom.domain.usuario.DadosLogin;
import com.myproject.pontocom.domain.usuario.Usuario;
import com.myproject.pontocom.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionarios")
@SecurityRequirement(name = "bearer-key")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{email}")
    public ResponseEntity dadosFuncionario(@PathVariable String email){
        var dados = funcionarioRepository.findByContatoEmail(email);
        return ResponseEntity.ok(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemFuncionarios>> listar(Pageable paginacao){
        var page = funcionarioRepository.findAll(paginacao).map(DadosListagemFuncionarios::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder){
        var funcionario = new Funcionario(dados);
        funcionarioRepository.save(funcionario);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        Usuario usuario = new Usuario();
        usuario.setLogin(dados.login());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuarioRepository.save(usuario);
        return  ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados){
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }
    @PutMapping("atualizar-senha/{email}")
    @Transactional
    public ResponseEntity atualizarSenha(@RequestBody @Valid DadosAtualizadoSenha dadosAtualizadoSenha, @PathVariable String email){
        Usuario usuario = usuarioRepository.getReferenceByLogin(email);
        usuario.setSenha(passwordEncoder.encode(dadosAtualizadoSenha.senha()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(true);
    }
}

