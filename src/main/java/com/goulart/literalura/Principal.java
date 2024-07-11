package com.goulart.literalura;

import com.goulart.literalura.dto.ResultadoApi;
import com.goulart.literalura.model.Autor;
import com.goulart.literalura.model.Idioma;
import com.goulart.literalura.model.Livro;
import com.goulart.literalura.repository.AutorRepository;
import com.goulart.literalura.repository.LivroRepository;
import com.goulart.literalura.service.ConsumoApi;
import com.goulart.literalura.service.ConverteDados;
import com.goulart.literalura.service.ConverteLivro;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

        private static Scanner leitor = new Scanner(System.in);
        private static final String gutendexApi = "https://gutendex.com/books/";
        private static final ConsumoApi consumoApi = new ConsumoApi();
        private static final ConverteDados conversor = new ConverteDados();
        private static final String buscaPeloId = "?ids=";
        private static final String buscaPorNome = "?search=";
        private LivroRepository livroRepository;
        private AutorRepository autorRepository;
        private ConverteLivro converteLivroService;

        public Principal(AutorRepository autorRepository, LivroRepository livroRepository, ConverteLivro converteLivroService) {
                this.autorRepository = autorRepository;
                this.livroRepository = livroRepository;
                this.converteLivroService = converteLivroService;
        }


        public void menu() {

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
                                case 5 -> buscaPorIdioma();
                                case 6 -> buscaRanking();                                
                        }
                } while (opcao != 0);
                retornoSaida();
        }


        private void buscaRanking() {
                try {
                        System.out.println("Buscando os 10 livros mais baixados...");
                        Pageable topTen = PageRequest.of(0, 10);
                        List<Livro> livrosMaisBaixados = livroRepository.findTop10ByOrderByNumeroDownloadsDesc(topTen);

                        if (livrosMaisBaixados.isEmpty()) {
                                System.out.println("Nenhum livro encontrado.");
                        } else {
                                int posicao = 1; // Inicia a contagem da posição
                                for (Livro livro : livrosMaisBaixados) {
                                        System.out.println("\nPosição " + posicao + "\n" + livro + "\n-----------");
                                        posicao++; // Incrementa a posição para o próximo livro
                                }
                        }
                } catch (Exception e) {
                        System.out.println("Ocorreu um erro durante a busca: " + e.getMessage());
                }
                menu();
        }

        private void buscaPorIdioma() {
                try {
                        System.out.println("Digite o idioma do livro (por exemplo, 'pt' para Português): ");
                        leitor.nextLine();
                        String idiomaDigitado = leitor.nextLine().toUpperCase();
                        Optional<Idioma> idioma = Idioma.porCodigo(idiomaDigitado);

                        if (idioma.isPresent()) {
                                List<Livro> livrosEncontrados = livroRepository.findByidiomas(idioma.get());
                                if (livrosEncontrados.isEmpty()) {
                                        System.out.println("Nenhum livro encontrado para o idioma: " + idioma.get().getNomeAmigavel());
                                } else {
                                        livrosEncontrados.forEach(livro -> System.out.println(livro));
                                }
                        } else {
                                System.out.println("Idioma inválido. Por favor, tente novamente.");
                        }
                } catch (Exception e) {
                        System.out.println("Ocorreu um erro durante a busca: " + e.getMessage());
                }
                menu();
                retornoSaida();
        }

        public void salvarDB(Livro livro, Autor autor) {
                livroRepository.save(livro);
                if (autor != null) { // Verifica se o autor não é nulo antes de salvar
                        autorRepository.save(autor);
                }
        }

        private void buscaEntreAnos() {
                try {
                        System.out.println("""
                                Use-os para encontrar livros de autores vivos entre dois anos. Por exemplo:
                                Digitando '500 1000' a busca fornece livros com autores vivos entre 500 e 1000 D.C.
                                Digite o período desejado:
                                """);
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
                retornoSaida();
        }

        private void buscaPorPeriodo() {
                try {
                        System.out.println("""
                                Use-os para encontrar livros com pelo menos um autor vivo em um determinado período.Por exemplo:
                                Digitando '-499' a busca fornece livros com autores vivos antes de 500 a.C.
                                Digite o período desejado:
                                """);
                        int anoBase = leitor.nextInt();
                        // Verifica se a URL já contém um '?', se não, adiciona
                        String url = gutendexApi.contains("?") ? gutendexApi + "&author_year_end=" + anoBase
                                : gutendexApi + "?author_year_end=" + anoBase;
                        exibeLivro(url);
                } catch (InputMismatchException e) {
                        System.out.println("Por favor, insira um número inteiro válido.");
                }
                menu();
                retornoSaida();
        }

        private void buscaPorNome() {
                System.out.println("Digite o nome do livro ou autor: ");
                leitor.nextLine();
                String nomeDigitadoParaBusca = leitor.nextLine(); // Usa nextLine() para capturar toda a linha, incluindo espaços
                String url = gutendexApi + buscaPorNome + nomeDigitadoParaBusca.replaceAll("\\s+", "%20");
                exibeLivro(url);
                menu();
                retornoSaida();
        }

        private void buscaDetalhesPorId() {
                System.out.println("Digite o ID do livro: ");
                int id = leitor.nextInt();
                String url = gutendexApi + buscaPeloId + id;
                exibeLivro(url);
                retornoSaida();
        }

        private List<Livro> exibeLivro(String url) {
                try {
                        var json = consumoApi.obterDados(url);
                        System.out.println("Resultado encontrado: ");
                        ResultadoApi api = conversor.obterDados(json, ResultadoApi.class);
                        List<Livro> livros = api.listaDeLivros().stream()
                                .map(detalhe -> converteLivroService.converterParaLivro(detalhe))
                                .peek(livro -> {
                                        System.out.println(livro);
                                        salvarDB(livro, livro.getAutor());
                                })
                                .collect(Collectors.toList());
                        return livros;
                } catch (Exception e) {
                        System.out.println("Ocorreu um erro ao buscar por nome: " + e.getMessage());
                        return new ArrayList<>();
                }
        }

        private void retornoSaida() {
                leitor.nextLine();
                System.out.println("\nPressione qualquer tecla para voltar ao menu ou 0 para sair.");
                String entrada = leitor.nextLine();
                if ("0".equals(entrada.trim())) {
                        System.exit(0);
                } else {
                        menu();
                }
        }
}