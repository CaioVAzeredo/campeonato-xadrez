package com.example.camp_xadrez.api.domain;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String mensagem){
        super(mensagem);
    }
}
