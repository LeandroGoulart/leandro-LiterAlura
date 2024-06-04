package com.goulart.leandro_LiterAlura.service;

import com.goulart.leandro_LiterAlura.dto.LivroDTO;
import com.goulart.leandro_LiterAlura.model.Livro;
import com.goulart.leandro_LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repositorio;

    public LivroDTO obterLivros() {
        Livro livro = repositorio.findById(1L).get();

        return new LivroDTO(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getDescricao(), livro.getUrlCapa());
    }
}
