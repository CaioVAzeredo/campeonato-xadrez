package com.example.camp_xadrez.api.controller;

import com.example.camp_xadrez.api.domain.aluno.AlunoRepository;
import com.example.camp_xadrez.api.domain.aluno.DadosListagemAlunos;
import com.example.camp_xadrez.api.domain.turma.TurmaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aluno")
@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemAlunos>> listar(@ParameterObject @PageableDefault(size = 10, sort = "turma", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAlunos::new);
        return ResponseEntity.ok(page);
    }

}
