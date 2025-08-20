package com.example.camp_xadrez.api.controller;

import com.example.camp_xadrez.api.domain.aluno.*;
import com.example.camp_xadrez.api.domain.turma.Turma;
import com.example.camp_xadrez.api.domain.turma.TurmaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("aluno")
@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    TurmaRepository turmaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        Aluno aluno = new Aluno(dados);
        repository.save(aluno);
        var uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAlunos>> listar(@ParameterObject @PageableDefault(size = 10, sort = "turma", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAlunos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarAluno dados) {
        Aluno aluno = repository.getReferenceById(id);

        Turma turmaRef = null;
        if (dados.id_turma() != null) {
            turmaRef = turmaRepository.getReferenceById(dados.id_turma());
        }
        aluno.atualizarAluno(dados, turmaRef);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();

        return ResponseEntity.ok("ALuno " + aluno.getNome() + "excluído(a) com sucesso! ");
    }

}