package com.goulart.literalura.model;

import java.util.Arrays;
import java.util.Optional;

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

    public static Optional<Idioma> porCodigo(String codigo) {
        return Arrays.stream(Idioma.values())
                .filter(idioma -> idioma.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public static Optional<Idioma> porNomeAmigavel(String nomeAmigavel) {
        return Arrays.stream(Idioma.values())
                .filter(idioma -> idioma.getNomeAmigavel().equalsIgnoreCase(nomeAmigavel))
                .findFirst();
    }
}