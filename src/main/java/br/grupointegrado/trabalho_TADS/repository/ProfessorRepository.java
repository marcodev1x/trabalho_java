package br.grupointegrado.trabalho_TADS.repository;

import br.grupointegrado.trabalho_TADS.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
