package com.goulart.leandro_LiterAlura.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Book;
import java.util.List;

public interface RepositorioLivro extends JpaRepository<Livro,Long> {

    boolean existsByNome(String nome);

    @Query("SELECT DISTINCT l.idioma FROM Livro l ORDER BY l.idioma")
    List<String> bucasidiomas();

    @Query("SELECT l FROM Livro l WHERE idioma = :idiomaSelecionado")
    List<Livro> buscarPorIdioma(String idiomaSelecionado);

    Book findByNome(String nome);
}