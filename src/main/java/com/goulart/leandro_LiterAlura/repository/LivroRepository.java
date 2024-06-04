package com.goulart.leandro_LiterAlura.repository;

import com.goulart.leandro_LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
