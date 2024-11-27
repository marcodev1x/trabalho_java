package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.NotaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Disciplina;
import br.grupointegrado.trabalho_TADS.model.Matricula;
import br.grupointegrado.trabalho_TADS.model.Nota;
import br.grupointegrado.trabalho_TADS.repository.DisciplinaRepository;
import br.grupointegrado.trabalho_TADS.repository.MatriculaRepository;
import br.grupointegrado.trabalho_TADS.repository.NotasRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Nota>> findAll() {
        List<Nota> notas = this.repository.findAll();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    @JsonIgnoreProperties({"alunos", "disciplinas"})
    public ResponseEntity<Nota> findById(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao procurar nota por ID"));
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    @JsonIgnoreProperties("disciplinas")
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar disciplina por ID"));
        Matricula matricula = matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar matrícula por ID"));

        Nota nota = new Nota();
        nota.setDisciplina(disciplina);
        nota.setMatricula(matricula);
        nota.setData_lancamento(dto.data_lancamento());
        nota.setNota(dto.nota());

        Nota notaSalva = this.repository.save(nota);
        return ResponseEntity.status(201).body(notaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao deletar por ID"));

        this.repository.delete(nota);
        return ResponseEntity.noContent().build();
    }
}
