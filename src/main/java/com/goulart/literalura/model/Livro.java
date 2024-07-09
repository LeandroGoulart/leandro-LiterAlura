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
    private List<Autor> autor;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer numeroDownloads;

    @Override
    public String toString() {
        return  "Id=" + id + "\n" +
                "Titulo=" + titulo + "\n" +
                "Autor=" + autor.get(0) + "\n" +
                "Idiomas=" + idiomas + "\n" +
                "Número de downloads: =" + numeroDownloads + "\n";
    }
}