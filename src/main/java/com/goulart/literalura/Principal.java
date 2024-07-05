package com.goulart.literalura;

import com.goulart.literalura.dto.DadosLivro;
import com.goulart.literalura.dto.DetalhesLivro;
import com.goulart.literalura.service.ConsumoApi;
import com.goulart.literalura.service.ConverteDados;

import java.util.List;


public class Principal {

        public static void menu() {

//        System.out.println("""
//                Selecione uma opção:
//                1. Consultar livros disponíveis
//                2. Buscar autores por nome
//                3. Buscar autores por nascimento
//                4. Listar autores vivos em determinado período
//                5. Busca de livros por idioma
//                6. Top 10 livros mais baixados
//                0. Sair
//                """);
//
                final String gutendexApi = "https://gutendex.com/books/";
                final String espaço = "%20";
                final String buscaPeloId = "?ids=54130";

                var consumoApi = new ConsumoApi();
                var json = consumoApi.obterDados(gutendexApi + buscaPeloId);

                ConverteDados conversor = new ConverteDados();
                DetalhesLivro detalhesLivro = conversor.obterDados(json, DetalhesLivro.class);
                List<DadosLivro> listaDadosLivro = detalhesLivro.resultados();

                for (DadosLivro dadosLivro : listaDadosLivro) {
                        System.out.println(dadosLivro);
                }
        }
}