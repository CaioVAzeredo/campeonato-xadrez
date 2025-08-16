package com.example.camp_xadrez.api.domain.turma;

import java.time.LocalDateTime;

public class Turma {
    private Long id_turma;
    private Long id_professor;
    private String nome;
    private String descricao;
    private Integer ano_letivo;
    private boolean ativo;
    private LocalDateTime data_criacao = LocalDateTime.now();
    private LocalDateTime data_atualizacao = LocalDateTime.now();

    public Turma(DadosCadastroTurma dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.ano_letivo = dados.ano_letivo();
        this.ativo = dados.ativo();
    }

    public Long getId_turma() {
        return id_turma;
    }

    public void setId_turma(Long id_turma) {
        this.id_turma = id_turma;
    }

    public Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(Long id_professor) {
        this.id_professor = id_professor;
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

    public void atualizarTurma(){
        //ainda falta adicionar
    }
    public void excluir(){
        this.ativo = false;
    }
}