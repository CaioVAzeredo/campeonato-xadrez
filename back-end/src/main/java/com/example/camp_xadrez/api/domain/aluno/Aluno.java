package com.example.camp_xadrez.api.domain.aluno;

import com.example.camp_xadrez.api.domain.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "aluno")
@Entity(name = "Aluno")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_turma", nullable = false, foreignKey = @ForeignKey(name = "fk_aluno_turma"))
    private Turma turma;
    private String nome;
    private Float pontos_total;
    private boolean ativo;
    private LocalDateTime data_criacao = LocalDateTime.now();
    private LocalDateTime ultima_atualizacao = LocalDateTime.now();

    public Aluno(DadosCadastroAluno dados) {
        this.nome = dados.nome();
        this.pontos_total = dados.pontos_total();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPontos_total() {
        return pontos_total;
    }

    public void setPontos_total(Float pontos_total) {
        this.pontos_total = pontos_total;
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

    public LocalDateTime getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(LocalDateTime ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public void atualizarAluno(DadosAtualizarAluno dados, Turma turmaRefId) {
        if (turmaRefId != null) {
            this.turma = turmaRefId;
        }

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.pontos_total() != null) {
            this.pontos_total = dados.pontos_total();
        }

        if(dados.id_turma() != null){
            this.turma = turmaRefId;
        }

        this.ultima_atualizacao = LocalDateTime.now();
    }

    public void excluir() {
        this.ativo = false;
    }

}