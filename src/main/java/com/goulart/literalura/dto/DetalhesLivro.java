package com.goulart.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetalhesLivro(
        @JsonAlias("count") Integer contagem,
        @JsonAlias("next") String proximo,
        @JsonAlias("previous") String anterior,
        @JsonAlias("results") List<DadosLivro> resultados
){}