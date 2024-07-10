package com.myproject.pontocom.domain.registroDePresenca;

import com.myproject.pontocom.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    Registro getReferenceByDataAndIdFuncionario(LocalDate now, Long id);
}
