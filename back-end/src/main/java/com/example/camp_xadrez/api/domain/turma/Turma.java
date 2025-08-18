package com.example.camp_xadrez.api.domain.turma;

import com.example.camp_xadrez.api.domain.professor.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "turma")
@Entity(name = "Turma")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_professor", nullable = false, foreignKey = @ForeignKey(name = "fk_turma_professor")
    )
    private Professor professor;
    private String nome;
    private String descricao;
    private Integer ano_letivo;
    private boolean ativo;
    private LocalDateTime data_criacao = LocalDateTime.now();
    private LocalDateTime data_atualizacao = LocalDateTime.now();

    public Turma(DadosCadastroTurma dados) {
        this.professor = dados.professor();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.ano_letivo = dados.ano_letivo();
        this.ativo = true;
    }

    public Long getId_turma() {
        return id;
    }

    public void setId_turma(Long id_turma) {
        this.id = id_turma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAno_letivo() {
        return ano_letivo;
    }

    public void setAno_letivo(Integer ano_letivo) {
        this.ano_letivo = ano_letivo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDateTime getData_atualizacao() {
        return data_atualizacao;
    }

    public void setData_atualizacao(LocalDateTime data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }

    public void atualizarTurma(DadosAtualizarTurma dados) {
        if (dados.professor() != null) {
            this.professor = dados.professor();
        }

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }

        if (dados.ano_letivo() != null) {
            this.ano_letivo = dados.ano_letivo();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}