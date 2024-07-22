package com.myproject.pontocom.domain.registroDePresenca;

import com.myproject.pontocom.domain.registroDePresenca.ausencia.Presente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    long idFuncionario;
    private LocalDate data = LocalDate.now();
    private String horarioDeEntrada;
    private String horarioDeIntervalo;
    private String horarioDeSaida;

    @Embedded
    private Presente presente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHorarioDeIntervalo() {
        return horarioDeIntervalo;
    }

    public void setHorarioDeIntervalo(String horarioDeIntervalo) {
        this.horarioDeIntervalo = horarioDeIntervalo;
    }

    public String getHorarioDeEntrada() {
        return horarioDeEntrada;
    }

    public void setHorarioDeEntrada(String horarioDeEntrada) {
        this.horarioDeEntrada = horarioDeEntrada;
    }

    public String getHorarioDeSaida() {
        return horarioDeSaida;
    }

    public void setHorarioDeSaida(String horarioDeSaida) {
        this.horarioDeSaida = horarioDeSaida;
    }



    public void marcarEntrada(String dataDeEntrada, long id) {
        setData(LocalDate.now());

        this.presente = new Presente();
        presente.setFuncionarioPresente(true);
        setHorarioDeEntrada(dataDeEntrada);
        setIdFuncionario(id);
    }

    public void marcarIntervalo(String horaDeIntervalo) {
        setHorarioDeIntervalo(horaDeIntervalo);
    }

    public void marcarSaida(String horaDeSaida) {
        setHorarioDeSaida(horaDeSaida);
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", idFuncionario=" + idFuncionario +
                ", data=" + data +
                ", horarioDeEntrada='" + horarioDeEntrada + '\'' +
                ", horarioDeIntervalo='" + horarioDeIntervalo + '\'' +
                ", horarioDeSaida='" + horarioDeSaida + '\'' +
                ", presente=" + presente +
                '}';
    }



    public void setPresente(Presente presente) {
        this.presente = presente;
    }

    public Presente getPresente() {
        return presente;
    }
}
