package com.goulart.leandro_LiterAlura.repository;

import com.goulart.leandro_LiterAlura.model.DadosAutor;
import com.goulart.leandro_LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String nomeLivro);

    List<DadosAutor> findByAutoresNomeContainingIgnoreCase(String nomeAutor);




}
