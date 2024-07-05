package com.goulart.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.goulart.literalura.model.Autor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") Autor autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer contagemDownloads
){}

