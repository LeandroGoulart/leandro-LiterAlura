package com.goulart.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Autor {
    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer anoNascimento;

    @JsonAlias("death_year")
    private Integer anoMorte;

    @Override
    public String toString() {
        return   "\n" + "Nome: " + nome + " " + "( " +
                "Ano de nascimento: " + anoNascimento + " " +
                "Ano de morte: " + anoMorte + ")" + "\n";
    }
}