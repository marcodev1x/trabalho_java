package br.grupointegrado.trabalho_TADS.repository;

import br.grupointegrado.trabalho_TADS.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotasRepository extends JpaRepository<Nota, Integer> {
}
