package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.MatriculaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Aluno;
import br.grupointegrado.trabalho_TADS.model.Matricula;
import br.grupointegrado.trabalho_TADS.model.Turma;
import br.grupointegrado.trabalho_TADS.repository.AlunoRepository;
import br.grupointegrado.trabalho_TADS.repository.MatriculaRepository;
import br.grupointegrado.trabalho_TADS.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        List<Matricula> matriculas = matriculaRepository.findAll();
        return ResponseEntity.ok(matriculas);
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody MatriculaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Aluno não encontrado."));
        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Turma não encontrada."));

        Matricula matricula = new Matricula();
        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        Matricula savedMatricula = matriculaRepository.save(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatricula);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Matrícula não encontrada."));
        return ResponseEntity.ok(matricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Integer id, @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Matrícula não encontrada para atualização."));

        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Aluno não encontrado."));
        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro: Turma não encontrada."));

        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        Matricula updatedMatricula = matriculaRepository.save(matricula);
        return ResponseEntity.ok(updatedMatricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Matrícula não encontrada para exclusão."));
        matriculaRepository.delete(matricula);
        return ResponseEntity.noContent().build();
    }
}
