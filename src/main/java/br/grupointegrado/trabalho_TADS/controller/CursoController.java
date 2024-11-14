package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.CursoRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @GetMapping
    public List<Curso> findAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public Curso save(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return this.repository.save(curso);
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao achar por ID"));
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao editar"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return this.repository.save(curso);
    }

    @DeleteMapping("/{id}   ")
    public void delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro ao excluir"));
        this.repository.delete(curso);
    }
}
