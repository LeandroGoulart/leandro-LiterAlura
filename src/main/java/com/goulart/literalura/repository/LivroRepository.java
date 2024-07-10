package com.goulart.literalura.repository;

import com.goulart.literalura.model.Idioma;
import com.goulart.literalura.model.Livro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l JOIN FETCH l.idiomas li WHERE li.idioma = :idioma")
    List<Livro> findByidiomas(Idioma idioma);

    @Query("SELECT l FROM Livro l JOIN FETCH l.idiomas ORDER BY l.numeroDownloads DESC")
    List<Livro> findTop10ByOrderByNumeroDownloadsDesc(Pageable pageable);
}