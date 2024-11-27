package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.NotaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Aluno;
import br.grupointegrado.trabalho_TADS.model.Disciplina;
import br.grupointegrado.trabalho_TADS.model.Matricula;
import br.grupointegrado.trabalho_TADS.model.Nota;
import br.grupointegrado.trabalho_TADS.repository.AlunoRepository;
import br.grupointegrado.trabalho_TADS.repository.DisciplinaRepository;
import br.grupointegrado.trabalho_TADS.repository.MatriculaRepository;
import br.grupointegrado.trabalho_TADS.repository.NotasRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotasRepository repository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    @JsonIgnoreProperties({"alunos", "disciplinas"})
    public List<Nota> findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    @JsonIgnoreProperties({"alunos", "disciplinas"})
    public Nota findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao procurar nota por" +
                " ID"));
    }

    @PostMapping
    @JsonIgnoreProperties("disciplinas")
    public Nota save(@RequestBody NotaRequestDTO dto) {
        Disciplina disciplina =
                disciplinaRepository.findById(dto.disciplina_id()).orElseThrow(() -> new IllegalArgumentException(
                        "Erro ao buscar disciplina por ID"));
        Matricula matricula =
                matriculaRepository.findById(dto.disciplina_id()).orElseThrow(() -> new IllegalArgumentException(
                        "Erro ao buscar matrícula por ID"));

        Nota nota = new Nota();
        nota.setDisciplina(disciplina);
        nota.setMatricula(matricula);
        nota.setData_lancamento(dto.data_lancamento());
        nota.setNota(dto.nota());

        return this.repository.save(nota);
    }

    @DeleteMapping
    public Nota delete(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao deletar por ID"));
    }
}
