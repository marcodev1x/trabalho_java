package br.grupointegrado.trabalho_TADS.repository;

import br.grupointegrado.trabalho_TADS.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
