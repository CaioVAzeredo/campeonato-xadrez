package com.example.camp_xadrez.api.domain.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;


public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    @EntityGraph(attributePaths = "turma")
    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);
}