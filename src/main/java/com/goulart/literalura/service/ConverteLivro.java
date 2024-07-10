package com.goulart.literalura.service;

import com.goulart.literalura.dto.DetalhesLivro;
import com.goulart.literalura.model.Autor;
import com.goulart.literalura.model.Idioma;
import com.goulart.literalura.model.Livro;
import com.goulart.literalura.model.LivroIdioma;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ConverteLivro implements iConverteLivro {

    @Override
    public Livro converterParaLivro(DetalhesLivro detalhe) {
        Autor autor = null;
        if (detalhe.getPrimeiroAutor() != null) {
            autor = new Autor();
            autor.setNome(detalhe.getPrimeiroAutor().nome());
            autor.setAnoNascimento(detalhe.getPrimeiroAutor().anoNascimento());
            autor.setAnoMorte(detalhe.getPrimeiroAutor().anoMorte());
        }

        Livro livro = new Livro(detalhe.id(), detalhe.titulo(), autor, null, detalhe.numeroDownloads());

        List<LivroIdioma> idiomasConvertidos = detalhe.idiomas().stream()
                .flatMap(codigo -> Arrays.stream(codigo.split(",")))
                .distinct()
                .map(Idioma::porCodigo)
                .flatMap(Optional::stream) // Change this line to properly handle Optional
                .map(idioma -> new LivroIdioma(livro, idioma))
                .collect(Collectors.toList());

        livro.setIdiomas(idiomasConvertidos);

        return livro;
    }
}