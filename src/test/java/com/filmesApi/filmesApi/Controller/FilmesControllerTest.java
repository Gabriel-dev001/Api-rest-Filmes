package com.filmesApi.filmesApi.Controller;

import com.filmesApi.filmesApi.Model.Filme;
import com.filmesApi.filmesApi.Service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FilmesControllerTest {

    @InjectMocks
    private FilmesController filmesController;

    @Mock
    private FilmeService filmeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmesController).build();
    }

    @Test
    void testBuscarFilmes() throws Exception {
        Filme filme1 = new Filme(1L, "Filme A", 2022, "Descrição A", 8.5f);
        Filme filme2 = new Filme(2L, "Filme B", 2023, "Descrição B", 7.5f);
        List<Filme> filmes = Arrays.asList(filme1, filme2);

        when(filmeService.listarFilmes()).thenReturn(filmes);

        // Testando a resposta da API
        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Filme A"))
                .andExpect(jsonPath("$[1].nome").value("Filme B"));
    }

    @Test
    void testSalvarFilme() throws Exception {
        Filme filmeNovo = new Filme(null, "Filme Novo", 2024, "Uma descrição do filme", 4.5f);
        Filme filmeSalvo = new Filme(1L, "Filme Novo", 2024, "Uma descrição do filme", 4.5f);

        when(filmeService.salvarFilme(filmeNovo)).thenReturn(filmeSalvo);

        mockMvc.perform(post("/filmes")
                        .contentType("application/json")
                        .content("{\"nome\":\"Filme Novo\",\"sinopse\":\"Uma descrição do filme\",\"anoLancamento\":2024,\"nota\":4.5}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Filme Novo"));
    }

    @Test
    void testSalvarFilmeComAnoNull() throws Exception {
        Filme filme = new Filme(null, "Filme Novo", 2024, null, null);

        mockMvc.perform(post("/filmes")
                        .contentType("application/json")
                        .content("{\"nome\":\"Filme Sem Ano\",\"descricao\":\"Descrição Sem Ano\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteFilme() throws Exception {
        Long filmeId = 1L;
        when(filmeService.deleteFilme(filmeId)).thenReturn(true);

        mockMvc.perform(delete("/filmes/{id}", filmeId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteFilmeNotFound() throws Exception {
        Long filmeId = 999L;
        when(filmeService.deleteFilme(filmeId)).thenReturn(false);

        mockMvc.perform(delete("/filmes/{id}", filmeId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFilme() throws Exception {
        Long filmeId = 1L;
        Filme filmeNovo = new Filme(filmeId, "Filme Atualizado", 2025, "Descrição Atualizada", null);
        Filme filmeAtualizado = new Filme(filmeId, "Filme Atualizado", 2025, "Descrição Atualizada", null);

        when(filmeService.updateFilme(filmeId, filmeNovo)).thenReturn(filmeAtualizado);

        mockMvc.perform(put("/filmes/{id}", filmeId)
                        .contentType("application/json")
                        .content("{\"nome\":\"Filme Atualizado\",\"descricao\":\"Descrição Atualizada\",\"anoLancamento\":2025}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Filme Atualizado"));
    }

    @Test
    void testUpdateFilmeNotFound() throws Exception {
        Long filmeId = 999L;
        Filme filmeNovo = new Filme(filmeId, "Filme Não Encontrado", 2025, "Descrição Não Encontrada", null);

        when(filmeService.updateFilme(filmeId, filmeNovo)).thenReturn(null);

        mockMvc.perform(put("/filmes/{id}", filmeId)
                        .contentType("application/json")
                        .content("{\"nome\":\"Filme Não Encontrado\",\"descricao\":\"Descrição Não Encontrada\",\"anoLancamento\":2025}"))
                .andExpect(status().isNotFound());
    }
}
