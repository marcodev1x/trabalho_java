package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.CursoRequestDTO;
import br.grupointegrado.trabalho_TADS.dto.MatriculaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Aluno;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.model.Matricula;
import br.grupointegrado.trabalho_TADS.model.Turma;
import br.grupointegrado.trabalho_TADS.repository.AlunoRepository;
import br.grupointegrado.trabalho_TADS.repository.MatriculaRepository;
import br.grupointegrado.trabalho_TADS.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Matricula> findAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public Matricula save(@RequestBody MatriculaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.aluno_id()).orElseThrow(() -> new IllegalArgumentException("Erro " +
                "ao encontrar aluno por id"));
        Turma turma = turmaRepository.findById(dto.turma_id()).orElseThrow(() -> new IllegalArgumentException("Erro " +
                "ao encontrar turma por id"));


        Matricula matricula = new Matricula();
        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        return this.repository.save(matricula);
    }

    @GetMapping("/{id}")
    public Matricula findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao achar por ID"));
    }

    @PutMapping("/{id}")
    public Matricula update(@PathVariable Integer id, @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao " +
                "editar"));

        Aluno aluno = alunoRepository.findById(dto.aluno_id()).orElseThrow(() -> new IllegalArgumentException("Erro " +
                "ao encontrar aluno por id"));
        Turma turma = turmaRepository.findById(dto.turma_id()).orElseThrow(() -> new IllegalArgumentException("Erro " +
                "ao encontrar turma por id"));

        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        return this.repository.save(matricula);
    }

    @DeleteMapping("/{id}   ")
    public void delete(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao " +
                "excluir"));
        this.repository.delete(matricula);
    }
}
