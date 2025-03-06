package com.filmesApi.filmesApi.Controller;

import com.filmesApi.filmesApi.Model.Filme;
import com.filmesApi.filmesApi.Service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Filmes API", description = "Operações sobre filmes")
@RestController
@RequestMapping("/filmes")
public class FilmesController{
    @Autowired
    private FilmeService filmeService;

    @Operation(summary = "Listar todos os filmes",
            description = "Recupera todos os filmes cadastrados.")
    @GetMapping
    public ResponseEntity<List<Filme>> buscarFilme() {
        List<Filme> filmes = filmeService.listarFilmes();
        return ResponseEntity.ok(filmes);
    }

    @Operation(summary = "Adicionar um filme",
            description = "Cria um novo filme no banco de dados.")
    @PostMapping
    public ResponseEntity<Filme> salvarFilme(@RequestBody Filme filme) {
        if (filme.getAnoLancamento() == null)
            return ResponseEntity.badRequest().body(null);
        Filme filmeSalvo = filmeService.salvarFilme(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeSalvo);
    }

    @Operation(summary = "Atualizar um filme",
            description = "Atualiza os dados de um filme existente.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Long id) {
        boolean deleted = filmeService.deleteFilme(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um filme",
            description = "Exclui um filme da base de dados.")
    @PutMapping("/{id}")
    public ResponseEntity<Filme> updateFilme(@PathVariable Long id, @RequestBody Filme filme) {
        Filme filmeAtualizado = filmeService.updateFilme(id, filme);
        if (filmeAtualizado != null) {
            return ResponseEntity.ok(filmeAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}