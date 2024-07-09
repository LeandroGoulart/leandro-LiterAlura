package com.goulart.literalura.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Idioma {
    INGLES("en", "Inglês"),
    FRANCES("fr", "Francês"),
    FINLANDES("fi", "Finlandês"),
    ESPANHOL("es", "Espanhol"),
    PORTUGUES("pt", "Português");

    private final String codigo;
    private final String nomeAmigavel;

    Idioma(String codigo, String nomeAmigavel) {
        this.codigo = codigo;
        this.nomeAmigavel = nomeAmigavel;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNomeAmigavel() {
        return nomeAmigavel;
    }

    public static List<Idioma> deStringSeparadaPorVirgulas(String idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(idiomas.split(","))
                .map(String::trim)
                .map(Idioma::porCodigo)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Idioma porCodigo(String codigo) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.getCodigo().equals(codigo)) {
                return idioma;
            }
        }
        return null;
    }
}