package br.pucminas.hospital.model;

import java.util.List;

/** Visão somente leitura do histórico de um paciente; não é persistida. */
public record HistoricoMedico(Paciente paciente, List<Consulta> consultas, List<Internacao> internacoes) {
}
