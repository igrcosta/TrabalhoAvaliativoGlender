package br.pucminas.hospital.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int numero;
    private int andar;
    private int capacidadeMaxima;

    @Enumerated(EnumType.STRING)
    private SituacaoQuarto situacao = SituacaoQuarto.DISPONIVEL;

    @OneToMany(mappedBy = "quarto")
    private List<Internacao> internacoes = new ArrayList<>();

    public int ocupacaoAtual() {
        return (int) internacoes.stream().filter(Internacao::isAtiva).count();
    }

    /** Regra 5: nunca ultrapassar a capacidade máxima. */
    public boolean temVaga() {
        return ocupacaoAtual() < capacidadeMaxima;
    }
}
