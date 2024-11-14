package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.ProfessorRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Aluno;
import br.grupointegrado.trabalho_TADS.model.Professor;
import br.grupointegrado.trabalho_TADS.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public List<Professor> findAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public Professor save(@RequestBody ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @GetMapping("/{id}")
    public Professor findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao buscar por ID"));
    }

    @PutMapping("/{id}")
    public Professor update(@PathVariable Integer id, @RequestBody ProfessorRequestDTO dto) {
        Professor professor = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro na " +
                "edição"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro na " +
                "exclusão"));

        this.repository.delete(professor);
    }
}
