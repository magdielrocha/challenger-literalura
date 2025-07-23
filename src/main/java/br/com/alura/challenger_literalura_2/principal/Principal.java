package br.com.alura.challenger_literalura_2.principal;

import br.com.alura.challenger_literalura_2.service.ConsultaApi;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsultaApi consultaApi = new ConsultaApi();

    private final String endereco = "https://gutendex.com/books/?search=";


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
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivorPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Encerrando o programa");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");

            }
        }


    }

    private void listarLivrosPorIdioma() {
    }

    private void listarAutoresVivorPorAno() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarLivrosRegistrados() {
    }

    public void buscarLivroPeloTitulo() {

        System.out.println("Digite o nome do livro: ");
        var nomeLivro = leitura.nextLine();
        var json = consultaApi.obterDados(endereco + nomeLivro.toLowerCase().trim().replace(" ", "+"));
        System.out.println(json);
    }



}
