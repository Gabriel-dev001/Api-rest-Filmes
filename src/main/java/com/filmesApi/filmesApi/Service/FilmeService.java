package com.filmesApi.filmesApi.Service;

import com.filmesApi.filmesApi.Model.Filme;
import com.filmesApi.filmesApi.Repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public boolean deleteFilme(Long id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Filme updateFilme(Long id, Filme filme) {
        Optional<Filme> filmeExistente = filmeRepository.findById(id);

        if (filmeExistente.isPresent()) {
            Filme filmeAtualizado = filmeExistente.get();

            filmeAtualizado.setNome(filme.getNome());
            filmeAtualizado.setAnoLancamento(filme.getAnoLancamento());
            filmeAtualizado.setSinopse(filme.getSinopse());
            filmeAtualizado.setNota(filme.getNota());

            return filmeRepository.save(filmeAtualizado);
        }
        return null;
    }
}