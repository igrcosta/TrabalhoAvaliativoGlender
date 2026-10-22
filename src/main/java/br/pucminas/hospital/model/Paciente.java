package br.pucminas.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Paciente extends Pessoa {

    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String endereco;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();

    @OneToMany(mappedBy = "paciente")
    private List<Internacao> internacoes = new ArrayList<>();

    public HistoricoMedico getHistorico() {
        return new HistoricoMedico(this, consultas, internacoes);
    }
}
