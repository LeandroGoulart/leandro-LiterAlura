package com.goulart.leandro_LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private int Nascimento;

    private int Obito;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}
    public Autor(DadosAutor dados) {
        this.nome = dados.nomeDoAutor();
        this.Nascimento = dados.nascimento();
        this.Obito = dados.obito();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNascimento() {
        return Nascimento;
    }

    public void setNascimento(int nascimento) {
        this.Nascimento = nascimento;
    }

    public int getObito() {
        return Obito;
    }

    public void setObito(int obito) {
        this.Obito = obito;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "---------------------------------------"+
                "\nNome: " + nome +
                "\nData De Nascimento: " + Nascimento +
                "\nData De Falecimento: " + Obito +
                "\n---------------------------------------";
    }
}