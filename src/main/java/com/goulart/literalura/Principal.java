package com.goulart.literalura;

import com.goulart.literalura.dto.DadosAutor;
import com.goulart.literalura.dto.DetalhesLivro;
import com.goulart.literalura.dto.ResultadoApi;
import com.goulart.literalura.model.Autor;
import com.goulart.literalura.model.Livro;
import com.goulart.literalura.service.ConsumoApi;
import com.goulart.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

        private static Scanner leitor = new Scanner(System.in);
        private static final String gutendexApi = "https://gutendex.com/books/";
        private static final ConsumoApi consumoApi = new ConsumoApi();
        private static final ConverteDados conversor = new ConverteDados();
        private static final String buscaPeloId = "?ids=";
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
                                case 2 -> buscaPorNome();
                                case 3 -> buscaPorPeriodo();
                                case 4 -> buscaEntreAnos();
                        }
                } while (opcao != 0);
        }


        private static void buscaEntreAnos() {
                try {
                        System.out.println("Use-os para encontrar livros de autores vivos entre dois anos. \n"
                                + "Por exemplo: Digitando '500 1000' a busca fornece livros com autores vivos entre 500 e 1000 d.C. \n" +
                                "Digite o período desejado: ");
                        System.out.println("Digite o ano inicial: ");
                        int anoBase = leitor.nextInt();
                        System.out.println("Digite o ano final: ");
                        int anoFinal = leitor.nextInt();
                        String url = String.format("%s?author_year_start=%d&author_year_end=%d", gutendexApi, anoBase, anoFinal);
                        exibeLivro(url); //
                } catch (InputMismatchException e) {
                        System.out.println("Por favor, insira um número inteiro válido.");
                        leitor.next();
                }
                menu();
        }

        private static void buscaPorPeriodo() {
                try {
                        System.out.println("Use-os para encontrar livros com pelo menos um autor vivo em um determinado período. \n"
                                + "Por exemplo: Digitando '-499' a busca fornece livros com autores vivos antes de 500 a.C. \n" +
                                "Digite o período desejado: ");
                        int anoBase = leitor.nextInt();
                        // Verifica se a URL já contém um '?', se não, adiciona
                        String url = gutendexApi.contains("?") ? gutendexApi + "&author_year_end=" + anoBase
                                : gutendexApi + "?author_year_end=" + anoBase;
                        exibeLivro(url);
                } catch (InputMismatchException e) {
                        System.out.println("Por favor, insira um número inteiro válido.");
                }
                menu();
        }

        private static void buscaPorNome() {
                System.out.println("Digite o nome do livro ou autor: ");
                leitor.nextLine();
                String nomeDigitadoParaBusca = leitor.nextLine(); // Usa nextLine() para capturar toda a linha, incluindo espaços
                String url = gutendexApi + buscaPorNome + nomeDigitadoParaBusca.replaceAll("\\s+", "%20");
                exibeLivro(url);
                menu();
                retornoSaida();
        }

        private static void buscaDetalhesPorId() {
                System.out.println("Digite o ID do livro: ");
                int id = leitor.nextInt();
                String url = gutendexApi + buscaPeloId + id;
                exibeLivro(url);
                retornoSaida();
        }

        private static List<Livro> exibeLivro(String url) {
                System.out.println("URL: " + url);
                try {
                        var json = consumoApi.obterDados(url);
                        System.out.println("Resultado encontrado: ");
                        ResultadoApi api = conversor.obterDados(json, ResultadoApi.class);
                        return api.listaDeLivros().stream()
                                .map(Principal::converterParaLivro)
                                .peek(System.out::println)
                                .collect(Collectors.toList());
                } catch (Exception e) {
                        System.out.println("Ocorreu um erro ao buscar por nome: " + e.getMessage());
                        return new ArrayList<>();
                }
        }

        private static Livro converterParaLivro(DetalhesLivro detalhe) {
                DadosAutor primeiroDadosAutor = detalhe.getPrimeiroAutor();
                Autor autor = null;
                if (primeiroDadosAutor != null) {
                        autor = new Autor();
                        autor.setNome(primeiroDadosAutor.nome());
                        autor.setAnoNascimento(primeiroDadosAutor.anoNascimento());
                        autor.setAnoMorte(primeiroDadosAutor.anoMorte());
                }
                return new Livro(
                        detalhe.id(),
                        detalhe.titulo(),
                        autor,
                        detalhe.idiomas(),
                        detalhe.numeroDownloads()
                );
        }

        private static void retornoSaida() {
                leitor.nextLine();
                System.out.println("\nPressione qualquer tecla para voltar ao menu ou 0 para sair.");
                String entrada = leitor.nextLine(); // Lê a próxima linha de entrada
                if ("0".equals(entrada.trim())) {
                        System.exit(0); // Encerra o programa
                } else {
                        menu(); // Exibe o menu novamente
                }
        }
}