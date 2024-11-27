package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.TurmaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.model.Turma;
import br.grupointegrado.trabalho_TADS.repository.CursoRepository;
import br.grupointegrado.trabalho_TADS.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Turma>> findAll() {
        List<Turma> turmas = repository.findAll();
        return ResponseEntity.ok(turmas);
    }

    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody TurmaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Curso não encontrado."));
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);
        Turma savedTurma = repository.save(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTurma);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Turma não encontrada."));
        return ResponseEntity.ok(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Curso não encontrado."));
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Turma não encontrada para edição."));
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);
        Turma updatedTurma = repository.save(turma);
        return ResponseEntity.ok(updatedTurma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Turma não encontrada para exclusão."));
        repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
