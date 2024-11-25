package br.grupointegrado.trabalho_TADS.controller;

import br.grupointegrado.trabalho_TADS.dto.DisciplinaRequestDTO;
import br.grupointegrado.trabalho_TADS.model.Curso;
import br.grupointegrado.trabalho_TADS.model.Disciplina;
import br.grupointegrado.trabalho_TADS.model.Professor;
import br.grupointegrado.trabalho_TADS.model.Turma;
import br.grupointegrado.trabalho_TADS.repository.CursoRepository;
import br.grupointegrado.trabalho_TADS.repository.DisciplinaRepository;
import br.grupointegrado.trabalho_TADS.repository.ProfessorRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.coyote.Response;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository profRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Disciplina> findAll() {
        return this.repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.curso_id()).orElseThrow(() -> new IllegalArgumentException("Erro " +
                "ao buscar o curso"));
        Professor professor =
                profRepository.findById(dto.professor_id()).orElseThrow(() -> new IllegalArgumentException("Erro ao " +
                        "encontrar professor"));

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDisciplina);
    }

    /*
    {
        "id": 1,
            "nome": "Programação Tópicos especiais 1",
            "codigo": "eee23414sa",
            "curso": {
        "id": 2,
                "nome": "Curso legal",
                "codigo": "222",
                "carga_horaria": 444,
                "turmas": [
        {
            "id": 1,
                "ano": 2024,
                "semestre": 2,
                "curso": 2
        }
    ],
        "disciplinas": [
        {
            "id": 1,
                "nome": "Programação Tópicos especiais 1",
                "codigo": "eee23414sa",
                "curso": 2,
                "professor": {
            "id": 3,
                    "nome": "Fabrício Pelloso Modificado",
                    "email": "fabpelloso1948@outlook.com",
                    "telefone": "44999933340",
                    "especialidade": "Testes de software"
        }
        }
    ]
    },
        "professor": {
        "id": 3,
                "nome": "Fabrício Pelloso Modificado",
                "email": "fabpelloso1948@outlook.com",
                "telefone": "44999933340",
                "especialidade": "Growth"
    }
    }
     */
}
