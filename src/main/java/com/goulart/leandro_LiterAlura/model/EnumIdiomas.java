package com.goulart.leandro_LiterAlura.model;

public enum EnumIdiomas {
    Ingles   ("en"),
    Portugues("pt"),
    Espanhol ("es");

    private String idiomaLivro;

    EnumIdiomas(String idiomaLivro) {
        this.idiomaLivro = idiomaLivro;
    }

    public static EnumIdiomas fromString (String idiomas) {
        for (EnumIdiomas idioma : EnumIdiomas.values()) {
            if (idiomas.equals(idioma.idiomaLivro)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Não foi encontrado: " + idiomas);
    }


}