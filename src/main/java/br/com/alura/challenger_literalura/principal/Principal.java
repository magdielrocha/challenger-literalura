package br.com.alura.challenger_literalura.principal;

import br.com.alura.challenger_literalura.model.Autor;
import br.com.alura.challenger_literalura.model.Livro;
import br.com.alura.challenger_literalura.service.AutorService;
import br.com.alura.challenger_literalura.service.LivroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private List<Livro> livros = new ArrayList<>();

    private List<Autor> autores = new ArrayList<>();

    private final LivroService livroService;
    private final AutorService autorService;

    public Principal(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;

    }


    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    Escolha o número de sua opção:
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    
                    0 - Sair
                    -------------------------------------------
                    """;

            System.out.println(menu);
            System.out.println("Digite sua opção: ");
            var entrada = leitura.nextLine().trim();

            if (entrada.isBlank() || !entrada.matches("\\d+")){
                mostrarErro("entrada");
                continue;
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                mostrarErro("entrada");
                continue;
            }

            switch (opcao) {
                case 1 -> buscarLivroPeloTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 ->listarAutoresRegistrados();
                case 4 -> listarAutoresVivorPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Encerrando o programa");
                default -> System.out.println("Opção inválida, tente novamente");

            }
        }
    }

    public static void mostrarErro(String tipo) {
        switch (tipo) {
            case "entrada" -> System.out.println("Número inválido. Tente novamente.\n");
            case "opcao" -> System.out.println("Entrada inválida. Tente novamente.\n");
            default -> System.out.println("Erro desconhecido...\n");
        }
    }


    private void listarLivrosPorIdioma() {
        var idiomaOpcoes = """
                Insira o idioma para realizar a busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """;

        Set<String> idiomasValidos = Set.of("pt", "en", "fr", "es");
        String entrada = "";
        while (true) {
            System.out.println(idiomaOpcoes);
            entrada = leitura.nextLine().trim().toLowerCase();

            if (!entrada.matches("[a-zA-Z]+")) {
                System.out.println("Opção inválida, tente novamente.");
                continue;
            }
            if (!idiomasValidos.contains(entrada)){
                System.out.println("Idioma não reconhecido! Digite uma das opções: pt, en, fr, es.");
            }
            break;
        }

        List<Livro> livrosPorIdioma = livroService.encontrarLivroPorIdioma(entrada);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        } else {
            System.out.println("Livros no idioma '" + entrada + "':");
            livrosPorIdioma.forEach(System.out::println);
        }

    }

    private void listarAutoresVivorPorAno() {
        System.out.println("Digite o ano que deseja pesquisar: ");
        var entrada = leitura.nextLine().trim();

        if (!entrada.matches("^\\d+$")) {
            System.out.println("Ano inválido. Tente novamente.\n");
            return;
        }

        var anoPesquisa = Integer.parseInt(entrada);
        List<Autor> autoresVivosPorAno = autorService.listarAutoresVivos(anoPesquisa);

        if(autoresVivosPorAno.isEmpty()){
            System.out.println("Nenhum autor vivo encontrado em " + anoPesquisa + ".");
        } else {
            System.out.println("Autores vivos em " + anoPesquisa + ": ");
            autoresVivosPorAno.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {

        List<Autor> autores = autorService.listarAutores();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(System.out::println);
        }

    }

    private void listarLivrosRegistrados() {
        livros = livroService.listarLivros();
        livros.forEach(System.out::println);
    }

    public void buscarLivroPeloTitulo() {
        System.out.println("Digite o nome do livro: ");
        var nomeLivro = leitura.nextLine();

        if (nomeLivro.isBlank()) {
            System.out.println("Você precisa digitar um nome válido!");
            return;
        }
        try {
            Livro livro = livroService.consultaLivroPelotitulo(nomeLivro);
            System.out.println(livro);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
