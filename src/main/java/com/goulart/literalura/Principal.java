package com.goulart.literalura;

import com.goulart.literalura.dto.DadosAutor;
import com.goulart.literalura.dto.DetalhesLivro;
import com.goulart.literalura.dto.ResultadoApi;
import com.goulart.literalura.service.ConsumoApi;
import com.goulart.literalura.service.ConverteDados;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

        // Declare o Scanner como uma variável de classe
        private static Scanner leitor;
        private static final String gutendexApi = "https://gutendex.com/books";
        private static ConsumoApi consumoApi = new ConsumoApi();
        private static ConverteDados converteDados = new ConverteDados();
        private static final String buscaPeloId = "/?ids=";
        private static final String buscaPorNome = "?search=";

        public static void menu() {

                // Inicialize o Scanner no método menu
                leitor = new Scanner(System.in);

                System.out.println("""
                
                Bem-vindo ao LiterAlura: Um sistema de busca de livros!
                MENU:
                1. Consultar livros por Id
                2. Buscar livros ou autores por nome
                3. Buscar livros por determinado período
                4. Listar livros de autores vivos entre anos
                5. Busca de livros por idioma
                6. Top 10 livros mais baixados
                0. Sair
                
                Digite uma opção válida: 
                """);

                int opcao = 0;
                do {
                        opcao = leitor.nextInt();
                        switch (opcao) {
                                case 1 -> buscaDetalhesPorId();
//                                case 2 -> buscaPorNome();
//                                case 3 -> buscaPorPeriodo();
//                                case 4 -> buscaEntreAnos();
                        }
                } while (opcao != 0);
        }


//        private static void buscaEntreAnos() {
//                try {
//                        System.out.println("Use-os para encontrar livros de autores vivos entre dois anos. \n"
//                                + "Por exemplo: Digitando '500 1000' a busca fornece livros com autores vivos entre 500 e 1000 d.C. \n" +
//                                "Digite o período desejado: ");
//                        System.out.println("Digite o ano inicial: ");
//                        int anoBase = leitor.nextInt();
//                        System.out.println("Digite o ano final: ");
//                        int anoFinal = leitor.nextInt();
//                        String url = String.format("%sauthor_year_start=%d&author_year_end=%d", gutendexApi, anoBase, anoFinal);
//                        exibeLivro(url, ResultadoApi.class);
//                } catch (InputMismatchException e) {
//                        System.out.println("Por favor, insira um número inteiro válido.");
//                }
//                menu();
//        }
//
//        private static void buscaPorPeriodo() {
//                try {
//                        System.out.println("Use-os para encontrar livros com pelo menos um autor vivo em um determinado período. \n"
//                                + "Por exemplo: Digitando '-499' a busca fornece livros com autores vivos antes de 500 a.C. \n" +
//                                "Digite o período desejado: ");
//                        int anoBase = leitor.nextInt();
//                        String url = String.format("%s&author_year_end=%d", gutendexApi, anoBase);
//                        exibeLivro(url, ResultadoApi.class);
//                } catch (InputMismatchException e) {
//                        System.out.println("Por favor, insira um número inteiro válido.");
//                }
//                menu();
//        }
//
//        private static void buscaPorNome() {
//                System.out.println("Digite o nome do livro ou autor: ");
//                try {
//                        String nome = leitor.next();
//                        String url = String.format("%s%s%s", gutendexApi, buscaPorNome, nome.replace(" ", "%20"));
//                        exibeLivro(url, ResultadoApi.class);
//                } catch (Exception e) {
//                        System.out.println("Ocorreu um erro ao buscar por nome: " + e.getMessage());
//                }
//                menu();
//        }

        private static void buscaDetalhesPorId() {
                System.out.println("Digite o ID do livro: ");
                int id = leitor.nextInt();
                String url = gutendexApi + buscaPeloId + id;
                exibeLivro(url);
        }

        private static void exibeLivro(String url) {
                var consumoApi = new ConsumoApi();
                System.out.println("URL: " + url);
                var json = consumoApi.obterDados(url);
                System.out.println("Resultado encontrado: ");
                ConverteDados conversor = new ConverteDados();
                ResultadoApi api = conversor.obterDados(json, ResultadoApi.class);

                if (api.listaDeLivros() != null && !api.listaDeLivros().isEmpty()) {
                        for (DetalhesLivro livro : api.listaDeLivros()) {
                                System.out.println("ID: " + livro.id());
                                System.out.println("Título: " + livro.titulo());
                                if (!livro.autor().isEmpty()) {
                                        DadosAutor primeiroAutor = livro.getPrimeiroAutor();
                                        System.out.println("Autor: " + primeiroAutor.nome());
                                        System.out.println("Ano de Nascimento do Autor: " + primeiroAutor.anoNascimento());
                                        System.out.println("Ano de Morte do Autor: " + primeiroAutor.anoMorte());
                                }
                                System.out.println("Idiomas: " + String.join(", ", livro.idiomas()));
                                System.out.println("Número de Downloads: " + livro.numeroDownloads());
                                System.out.println("--------------------------------");
                        }
                } else {
                        System.out.println("Nenhum livro encontrado.");
                }
        }
}