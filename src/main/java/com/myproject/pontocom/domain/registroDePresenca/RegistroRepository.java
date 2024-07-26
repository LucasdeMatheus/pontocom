package com.myproject.pontocom.domain.registroDePresenca;

import com.myproject.pontocom.domain.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    Registro getReferenceByDataAndIdFuncionario(LocalDate now, Long id);

   List< Registro> findAllByIdFuncionario(long id);


    Registro findAllByIdFuncionarioAndData(Long id, LocalDate data);

    @Query("SELECT COUNT(r) FROM Registro r WHERE r.idFuncionario = :idFuncionario AND r.presente.funcionarioPresente = true")
    int countByIdFuncionarioAndFuncionarioPresenteFalse(@Param("idFuncionario") long idFuncionario);

    @Query("SELECT COUNT(r) FROM Registro r WHERE r.idFuncionario = :idFuncionario AND (r.presente.funcionarioPresente = true OR r.presente.motivoAusencia IN (com.myproject.pontocom.domain.registroDePresenca.ausencia.MotivoAusencia.ATESTADO, com.myproject.pontocom.domain.registroDePresenca.ausencia.MotivoAusencia.FERIADO))")
    int countByIdFuncionarioAndFuncionarioPresenteTrue(@Param("idFuncionario") long idFuncionario);
}
