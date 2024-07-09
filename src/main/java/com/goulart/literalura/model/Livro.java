package com.goulart.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.goulart.literalura.dto.DadosAutor;
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
    private Autor autor;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer numeroDownloads;

    public Livro(Integer id, String titulo, Autor autor, List<String> idiomas, Integer numeroDownloads) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDownloads = numeroDownloads;
    }



    @Override
    public String toString() {
        String autorStr = autor == null ? "N/A" : autor.toString();
        return  "\nId= " + id + "\n" +
                "Titulo= " + titulo + "\n" +
                "Autor= " + autor +
                "Idiomas= " + idiomas + "\n" +
                "Número de downloads: " + numeroDownloads + "\n";
    }
}