package com.example.camp_xadrez.api.domain.professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Page<Professor> findAllByAtivoTrue(Pageable paginacao);
}
