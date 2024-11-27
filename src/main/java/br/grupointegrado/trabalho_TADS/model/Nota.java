package br.grupointegrado.trabalho_TADS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    @NotNull(message = "Matrícula não pode ser nula")
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    @NotNull(message = "Disciplina não pode ser nula")
    private Disciplina disciplina;

    @Column(name = "nota", nullable = false)
    @NotNull(message = "Nota não pode ser nula")
    private BigDecimal nota;

    @Column(name = "data_lancamento", nullable = false)
    @NotNull(message = "Data de lançamento não pode ser nula")
    private LocalDate dataLancamento;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setData_lancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
