package com.goulart.leandro_LiterAlura.controller;

import com.goulart.leandro_LiterAlura.dto.LivroDTO;
import com.goulart.leandro_LiterAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService servico;

    @GetMapping ("livros")
    public LivroDTO obterLivros() {
        return servico.obterLivros();
    }


}
