package com.goulart.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "livro_idiomas")
public class LivroIdioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private Idioma idioma;

    public LivroIdioma() {
    }

    public LivroIdioma(Livro livro, Idioma idioma) {
        this.livro = livro;
        this.idioma = idioma;
    }
}