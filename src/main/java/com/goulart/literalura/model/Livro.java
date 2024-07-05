package com.goulart.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Livro {
    @JsonAlias("id")
    private Integer id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<Autor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer contagemDownloads;
}