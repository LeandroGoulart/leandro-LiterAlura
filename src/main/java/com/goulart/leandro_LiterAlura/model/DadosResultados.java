package com.goulart.leandro_LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.print.Book;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultados(
        @JsonAlias("Resultados") List<Book> resultados
) {
}