package com.example.camp_xadrez.api.controller;

import com.example.camp_xadrez.api.domain.professor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("professor")
@SecurityRequirement(name = "bearer-key")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder) {
        Professor professor = new Professor(dados);
        repository.save(professor);
        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProfessor(professor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@ParameterObject @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemProfessor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarProfessor dados) {
        Professor professor = repository.getReferenceById(id);
        professor.atualizarProfessor(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Professor professor = repository.getReferenceById(id);
        professor.excluir();
        return ResponseEntity.noContent().build();
    }

}