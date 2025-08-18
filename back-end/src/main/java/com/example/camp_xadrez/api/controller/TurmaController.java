package com.example.camp_xadrez.api.controller;

import com.example.camp_xadrez.api.domain.turma.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("turma")
@SecurityRequirement(name = "bearer-key")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTurma dados, UriComponentsBuilder uriBuilder) {
        Turma turma = new Turma(dados);
        repository.save(turma);
        var uri = uriBuilder.path("/turma/{id}").buildAndExpand(turma.getId_turma()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTurma(turma));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTurma>> listar(@ParameterObject @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTurma::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarTurma dados){
        Turma turma = repository.getReferenceById(id);
        turma.atualizarTurma(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTurma(turma));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Turma turma = repository.getReferenceById(id);
        turma.excluir();
        return ResponseEntity.noContent().build();
    }

}
