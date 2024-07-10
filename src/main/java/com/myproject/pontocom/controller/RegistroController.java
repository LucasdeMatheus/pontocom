package com.myproject.pontocom.controller;

import com.myproject.pontocom.domain.registroDePresenca.Registro;
import com.myproject.pontocom.domain.registroDePresenca.RegistroRepository;
import com.myproject.pontocom.domain.registroDePresenca.ausencia.Presente;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/funcionarios")
@SecurityRequirement(name = "bearer-key")
public class RegistroController {

    @Autowired
    private RegistroRepository repository;
    private Registro registro;


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @PostMapping("/{id}/entrada")
    public ResponseEntity<Boolean> baterPonto(@PathVariable Long id){
        LocalDateTime agora = LocalDateTime.now();
        String horaDeEntrada = agora.format(formatter);

        this.registro = new Registro();
        registro.marcarEntrada(horaDeEntrada, id);
        repository.save(registro);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}/intervalo")
    @Transactional
    public ResponseEntity<Boolean> intervalo(@PathVariable Long id){
        LocalDateTime agora = LocalDateTime.now();
        String horaDeIntervalo = agora.format(formatter);

        var registro = repository.getReferenceByDataAndIdFuncionario(LocalDate.now(), id);
        registro.marcarIntervalo(horaDeIntervalo);

        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}/saida")
    @Transactional
    public ResponseEntity<Boolean> saida(@PathVariable Long id){
        LocalDateTime agora = LocalDateTime.now();
        String horaDeSaida = agora.format(formatter);

        var registro = repository.getReferenceByDataAndIdFuncionario(LocalDate.now(), id);
        registro.marcarSaida(horaDeSaida);

        return ResponseEntity.ok(true);
    }

}
