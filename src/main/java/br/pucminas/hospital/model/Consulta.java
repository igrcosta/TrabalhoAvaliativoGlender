package br.pucminas.hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    private ProfissionalSaude profissional;

    private LocalDate data;
    private LocalTime horario;
    private String motivo;
    private String observacoesMedicas;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status = StatusConsulta.AGENDADA;

    public void cancelar() {
        this.status = StatusConsulta.CANCELADA;
    }

    public void realizar(String observacoes) {
        this.observacoesMedicas = observacoes;
        this.status = StatusConsulta.REALIZADA;
    }
}
