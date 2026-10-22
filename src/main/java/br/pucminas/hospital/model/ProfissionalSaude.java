package br.pucminas.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProfissionalSaude extends Pessoa {

    @Column(unique = true)
    private String registroProfissional;
    private String especialidade;

    @OneToMany(mappedBy = "profissional")
    private List<Consulta> consultas = new ArrayList<>();

    /** Regra 3: sem dois atendimentos no mesmo horário. */
    public boolean disponivelEm(LocalDate data, LocalTime horario) {
        return consultas.stream().noneMatch(c ->
                c.getStatus() == StatusConsulta.AGENDADA
                        && c.getData().equals(data) && c.getHorario().equals(horario));
    }
}
