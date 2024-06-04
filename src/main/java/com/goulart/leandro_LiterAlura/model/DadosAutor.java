package com.goulart.leandro_LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name")String nomeDoAutor,
                         @JsonAlias("birth_year") Integer nascimento,
                         @JsonAlias("death_year") Integer obito) {
}