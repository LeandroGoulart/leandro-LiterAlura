package com.goulart.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "autores")
@Getter
@Setter
public class Autor {
    @Id
    @Column(name = "id_Autor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer anoNascimento;

    @JsonAlias("death_year")
    private Integer anoMorte;

    @Override
    public String toString() {
        return nome + " " + "( " +
                "Ano de nascimento: " + anoNascimento + " " +
                "Ano de morte: " + anoMorte + ")" + "\n";
    }
}