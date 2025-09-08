package com.example.camp_xadrez.api.domain.turma;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @EntityGraph(attributePaths = "professor")
    Page<Turma> findAllByAtivoTrue(Pageable paginacao);

    List<Turma> findByProfessorId(Long professorId);
}
