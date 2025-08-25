package com.example.camp_xadrez.api.controller;

import com.example.camp_xadrez.api.domain.aluno.Aluno;
import com.example.camp_xadrez.api.domain.aluno.AlunoRepository;
import com.example.camp_xadrez.api.domain.aluno.DadosCadastroAluno;
import com.example.camp_xadrez.api.domain.aluno.DadosDetalhamentoAluno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AlunoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroAluno> dadosCadastroAlunoJacksonTesterJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoAluno> dadosDetalhamentoAlunoJacksonTester;

    @MockitoBean
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception{
        var response = mvc
                .perform(post("/aluno"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception{

        var requestValido = new DadosCadastroAluno("Maria", 10.0f, 2L);
        DadosCadastroAluno dados = new DadosCadastroAluno("Maria", 10.0f, 1L);
        var entidadePersistida = new Aluno(dados);


        when(alunoRepository.save(any(Aluno.class))).thenReturn(entidadePersistida);

        var response = mvc.perform(
                post("/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroAlunoJacksonTesterJson.write(requestValido).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // Se o controller converte entidade -> DTO de saída com (id, nome, pontos):
        var detalhamentoEsperado = new DadosDetalhamentoAluno(1L, "Maria", 10.0f);
        var jsonEsperado = dadosDetalhamentoAlunoJacksonTester.write(detalhamentoEsperado).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}
