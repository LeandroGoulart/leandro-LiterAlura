package com.goulart.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonAlias("livro_id")
    private Integer id;

    @Column(name = "titulo")
    @JsonAlias("title")
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor")
    @JsonAlias("authors")
    private Autor autor;

    @Column(name = "idioma")
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroIdioma> idiomas;

    @Column(name = "numero_downloads")
    @JsonAlias("download_count")
    private Integer numeroDownloads;

    public Livro(Integer id, String titulo, Autor autor, List<LivroIdioma> idiomas, Integer numeroDownloads) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        String idiomasStr = idiomas.stream()
                .map(livroIdioma -> livroIdioma.getIdioma().getNomeAmigavel())
                .collect(Collectors.joining(", "));
        return "\nId= " + id +
                "\nTitulo= " + titulo +
                "\nAutor= " + (autor != null ? autor.getNome() + " ( Nascimento: " + autor.getAnoNascimento() + ", Morte: " + autor.getAnoMorte() + " )" : "N/A") +
                "\nIdiomas= " + idiomasStr +
                "\nNumero de Downloads= " + numeroDownloads;
    }
}