package com.goulart.literalura.service;

import com.goulart.literalura.dto.DetalhesLivro;
import com.goulart.literalura.model.Livro;

public interface iConverteLivro {
    Livro converterParaLivro(DetalhesLivro detalhe);
}