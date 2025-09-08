package com.example.camp_xadrez.api.controller;

import com.example.camp_xadrez.api.domain.professor.Professor;
import com.example.camp_xadrez.api.domain.professor.ProfessorRepository;
import com.example.camp_xadrez.api.domain.turma.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("turma")
@SecurityRequirement(name = "bearer-key")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    ProfessorRepository professorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTurma dados, UriComponentsBuilder uriBuilder) {
        Turma turma = new Turma(dados);

        var professorRef = professorRepository.getReferenceById(dados.idProfessor());
        turma.setProfessor(professorRef);

        repository.save(turma);
        var uri = uriBuilder.path("/turma/{id}").buildAndExpand(turma.getId_turma()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTurma(turma));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTurma>> listar(@ParameterObject @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTurma::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<Turma>> listarMinhasTurmas() {
        var professor = (Professor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var turmas = repository.findByProfessorId(professor.getId());
        return ResponseEntity.ok(turmas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarTurma dados) {
        Turma turma = repository.getReferenceById(id);

        Professor professorRef = null;
        if (dados.idProfessor() != null) {
            professorRef = professorRepository.getReferenceById(dados.idProfessor());
        }
        turma.atualizarTurma(dados, professorRef);
        return ResponseEntity.ok(new DadosDetalhamentoTurma(turma));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Turma turma = repository.getReferenceById(id);
        turma.excluir();

        return ResponseEntity.ok("Turma " + turma.getNome() + " excluída com sucesso!");
    }

}