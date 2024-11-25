package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.MatriculaRequestDTO;
import br.grupointegrado.trabalho_TADS.dto.TurmaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Aluno;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.model.Matricula;
import br.grupointegrado.trabalho_TADS.model.Turma;
import br.grupointegrado.trabalho_TADS.repository.CursoRepository;
import br.grupointegrado.trabalho_TADS.repository.TurmaRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.coyote.Request;
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

    @GetMapping("/{id}")
    public Turma findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao achar por ID"));
    }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id()).orElseThrow(() -> new IllegalArgumentException("Erro"));
        Turma turma = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao " +
                "editar"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        turma.setCurso(curso);

        return this.repository.save(turma);
    }

    @DeleteMapping("/{id}   ")
    public void delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao " +
                "excluir"));
        this.repository.delete(turma);
    }
}
