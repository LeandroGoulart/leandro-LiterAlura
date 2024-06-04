package com.goulart.leandro_LiterAlura.model;

public enum EnumIdiomas {
    Ingles("en"),
    Portugues("pt"),
    Espanhol("es");

    private String idiomaGutendex;

    EnumIdiomas(String idiomaGutendex) {
        this.idiomaGutendex = idiomaGutendex;
    }

    public static EnumIdiomas fromString (String idioma) {
        for (EnumIdiomas enumIdiomas : EnumIdiomas.values()) {
            if (enumIdiomas.idiomaGutendex.equalsIgnoreCase(idioma)) {
                return enumIdiomas;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma se encaixa aqui!");
    }
}