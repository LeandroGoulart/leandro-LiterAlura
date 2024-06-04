package com.goulart.leandro_LiterAlura.principal;

import com.goulart.leandro_LiterAlura.model.DadosAutor;
import com.goulart.leandro_LiterAlura.model.DadosLivro;
import com.goulart.leandro_LiterAlura.model.Livro;
import com.goulart.leandro_LiterAlura.repository.RepositorioAutor;
import com.goulart.leandro_LiterAlura.repository.RepositorioLivro;
import com.goulart.leandro_LiterAlura.service.ConversorDeDados;
import com.goulart.leandro_LiterAlura.service.RequisicaoApi;
import com.goulart.leandro_LiterAlura.model.Autor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private RequisicaoApi requisicao = new RequisicaoApi();
    private RepositorioAutor repositorioAutor;
    private RepositorioLivro repositorioLivro;
    private List<Livro> livros = new ArrayList<>();
    private ConversorDeDados conversor = new ConversorDeDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";

    public Principal(RepositorioAutor repositorioAutor, RepositorioLivro repositorioLivro) {
        this.repositorioAutor = repositorioAutor;
        this.repositorioLivro = repositorioLivro;
    }

    public void menu(){
        String menu = """
                **********************************************
                1 - Buscar livro pelo titulo
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma

                0 - Sair
                **********************************************
                """;
        var opcao = -1;
        while (opcao != 0){
            System.out.println(menu);
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao){
                case 1:
                    buscarNovoLivro();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarLivrosPorAno();
                    break;
                case 5:
                    buscarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Obrigado... Ate mais o/ ");
                    break;
                default:
                    System.out.println("\n\n*** Opção Inválida ***\n\n");
            }
        }
    }

    private void buscarNovoLivro() {
        System.out.println("\nQual livro deseja buscar?");
        var buscaDoUsuario = leitor.nextLine();
        var dados = requisicao.consumo(ENDERECO + buscaDoUsuario.replace(" ","%20"));
        SalvarBanco(dados);
    }

    private void SalvarBanco(String dados){
        try{
            Livro livro = new Livro(conversor.getData(dados, DadosLivro.class));
            Autor autor = new Autor(conversor.getData(dados, DadosAutor.class));
            Autor autorDb = null;
            Livro bookDb = null;
            if (!repositorioAutor.existsByNome(autor.getNome())){
                repositorioAutor.save(autor);
                autorDb = autor;
            }else{
                autorDb = repositorioAutor.findByNome(autor.getNome());
            }
            if (!repositorioLivro.existsByNome(livro.getNome())){
                livro.setAutor(autorDb);
                repositorioLivro.save(livro);
                bookDb = livro;
            }else{
                bookDb = repositorioLivro.findByNome(livro.getNome());
            }
            System.out.println(bookDb);
        }catch (NullPointerException e){
            System.out.println("\n\n*** Livro não encontrado ***\n\n");
        }
    }

    private void buscarLivrosRegistrados() {
        var bucasDB = repositorioLivro.findAll();
        if(!bucasDB.isEmpty()){
            System.out.println("\nLivros cadastrados no banco de dados: ");
            bucasDB.forEach(System.out::println);
        }else{
            System.out.println("\nNenhum livro encontrado no banco de dados!");
        }
    }

    private void buscarAutoresRegistrados() {
        var buscaDb = repositorioAutor.findAll();
        if(!buscaDb.isEmpty()){
            System.out.println("\nAutores cadastrados no banco de dados:");
            buscaDb.forEach(System.out::println);
        }else{
            System.out.println("\nNenhum autor encontrado no banco de dados!");
        }
    }

    private void buscarLivrosPorAno() {
        System.out.println("\nQual ano deseja pesquisar?");
        var anoSelecionado = leitor.nextInt();
        leitor.nextLine();
        var buscaAutoresNoDb = repositorioAutor.buscarPorAnoDeFalecimento(anoSelecionado);
        if(!buscaAutoresNoDb.collection.isEmpty()){
            System.out.println("\n\nAtores vivos no ano de: " + anoSelecionado);
            buscaAutoresNoDb.forEach(System.out::println);
        }else {
            System.out.println("\nNenhum autor encontrado para esta data!");
        }
    }

    private void buscarLivrosPorIdioma() {
        var idiomasCadastrados = repositorioLivro.bucasidiomas();
        System.out.println("\nIdiomas cadastrados no banco:");
        idiomasCadastrados.forEach(System.out::println);
        System.out.println("\nSelecione um dos idiomas cadastrados no banco:\n");
        var idiomaSelecionado = leitor.nextLine();
        repositorioLivro.buscarPorIdioma(idiomaSelecionado).forEach(System.out::println);
    }
}