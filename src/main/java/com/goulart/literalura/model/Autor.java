package com.goulart.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
@Getter
@Setter
@JsonDeserialize(using = Autor.AutorDeserializer.class)
public class Autor {
    @JsonAlias("name")
    private String nome;

    // getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    static class AutorDeserializer extends JsonDeserializer<Autor> {
        @Override
        public Autor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String firstAuthor = p.readValueAs(String[].class)[0];
            Autor autor = new Autor();
            autor.setNome(firstAuthor);
            return autor;
        }
    }
}