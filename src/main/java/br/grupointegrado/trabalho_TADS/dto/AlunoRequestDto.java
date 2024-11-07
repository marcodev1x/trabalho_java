package br.grupointegrado.trabalho_TADS.dto;

import java.time.LocalDate;

public record AlunoRequestDto(String nome, String email, String matricula, LocalDate data_nascimento) {
}
