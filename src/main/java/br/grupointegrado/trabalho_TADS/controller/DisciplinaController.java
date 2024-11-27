package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.DisciplinaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.model.Disciplina;
import br.grupointegrado.trabalho_TADS.model.Professor;
import br.grupointegrado.trabalho_TADS.repository.CursoRepository;
import br.grupointegrado.trabalho_TADS.repository.DisciplinaRepository;
import br.grupointegrado.trabalho_TADS.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = disciplinaRepository.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar o curso"));
        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro ao encontrar o professor"));

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDisciplina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao encontrar a disciplina com o ID fornecido"));
        return ResponseEntity.ok(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao atualizar: Disciplina não encontrada"));

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar o curso"));
        Professor professor = professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar o professor"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return ResponseEntity.ok(updatedDisciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao excluir: Disciplina não encontrada"));

        disciplinaRepository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
