# Diagrama de Classes - Sistema Hospitalar

Renderiza no GitHub/VS Code (preview Markdown) ou abra [diagrama-de-classes.html](diagrama-de-classes.html) no navegador para imprimir/exportar.

```mermaid
classDiagram
    direction TB

    class Pessoa {
        <<abstract>>
        -Long id
        -String nome
        -String telefone
        -String email
    }

    class Paciente {
        -String cpf
        -LocalDate dataNascimento
        -String endereco
        +getHistorico() HistoricoMedico
    }

    class ProfissionalSaude {
        -String registroProfissional
        -String especialidade
        +disponivelEm(LocalDate data, LocalTime horario) boolean
    }

    class Consulta {
        -Long id
        -LocalDate data
        -LocalTime horario
        -String motivo
        -String observacoesMedicas
        -StatusConsulta status
        +cancelar()
        +realizar(String observacoes)
    }

    class Internacao {
        -Long id
        -LocalDate dataEntrada
        -LocalDate dataPrevistaAlta
        -LocalDate dataEfetivaAlta
        -String observacoes
        +darAlta(LocalDate data)
        +isAtiva() boolean
    }

    class Quarto {
        -Long id
        -int numero
        -int andar
        -int capacidadeMaxima
        -SituacaoQuarto situacao
        +ocupacaoAtual() int
        +temVaga() boolean
    }

    class HistoricoMedico {
        +getConsultas() List~Consulta~
        +getInternacoes() List~Internacao~
    }

    class StatusConsulta {
        <<enumeration>>
        AGENDADA
        REALIZADA
        CANCELADA
    }

    class SituacaoQuarto {
        <<enumeration>>
        DISPONIVEL
        OCUPADO
    }

    Pessoa <|-- Paciente
    Pessoa <|-- ProfissionalSaude
    Paciente "1" --> "0..*" Consulta : realiza
    ProfissionalSaude "1" --> "0..*" Consulta : atende
    Paciente "1" --> "0..*" Internacao : possui
    ProfissionalSaude "1" --> "0..*" Internacao : responsável
    Quarto "1" --> "0..*" Internacao : aloca
    Paciente "1" -- "1" HistoricoMedico : possui
    HistoricoMedico o-- "0..*" Consulta
    HistoricoMedico o-- "0..*" Internacao
    Consulta ..> StatusConsulta
    Quarto ..> SituacaoQuarto
```

## Regras de negócio refletidas

| Regra | Onde fica |
|---|---|
| Paciente tem várias consultas/internações | associações `1 → 0..*` |
| Consulta tem paciente e profissional | `Consulta` ligada a `Paciente` e `ProfissionalSaude` |
| Profissional sem dois atendimentos no mesmo horário | `ProfissionalSaude.disponivelEm()` (validada no Service) |
| Internação exige quarto disponível | `Quarto.temVaga()` |
| Quarto não excede capacidade | `Quarto.capacidadeMaxima` + `ocupacaoAtual()` |
| Histórico de consultas e internações | `HistoricoMedico` |
