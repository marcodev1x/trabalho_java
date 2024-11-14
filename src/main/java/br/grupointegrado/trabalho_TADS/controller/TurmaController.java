package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.TurmaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.model.Turma;
import br.grupointegrado.trabalho_TADS.repository.CursoRepository;
import br.grupointegrado.trabalho_TADS.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {
    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    private List<Turma> findAll() {
        return this.repository.findAll();
    }

    @PostMapping
    private ResponseEntity<Turma> save(@RequestBody TurmaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id()).orElseThrow(() -> new IllegalArgumentException("Erro"));
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        return ResponseEntity.ok(this.repository.save(turma));
    }
}
