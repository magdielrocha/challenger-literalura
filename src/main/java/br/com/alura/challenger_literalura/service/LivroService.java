package br.com.alura.challenger_literalura.service;

import br.com.alura.challenger_literalura.model.Autor;
import br.com.alura.challenger_literalura.model.DadosLivro;
import br.com.alura.challenger_literalura.model.Livro;
import br.com.alura.challenger_literalura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final AutorService autorService;

    private final LivroRepository livroRepository;

    private final ConsultaApi consultaApi;

    private final ConverteDados conversor;

    private final String endereco = "https://gutendex.com/books/?search=";





    public LivroService(AutorService autorService, LivroRepository livroRepository, ConsultaApi consultaApi, ConverteDados conversor) {
        this.autorService = autorService;
        this.livroRepository = livroRepository;
        this.consultaApi = consultaApi;
        this.conversor = conversor;
    }

    @Transactional
    public Livro consultaLivroPelotitulo(String titulo) {
        String query = titulo.trim().replace(" ", "+");
        String json = consultaApi.obterDados(endereco + query);
        DadosLivro dados = conversor.obterLivro(json);
        Livro livro = criarLivro(dados);
        return livroRepository.save(livro);
    }

    public Livro criarLivro(DadosLivro dados) {
        List<Autor> autores = dados.autores()
                .stream()
                .map(a -> autorService.salvarAutor(
                        new Autor(a.nome(), a.anoNascimento(), a.anoMorte()))
                )
                .collect(Collectors.toList());

        Livro livro = new Livro();
        livro.setTitulo(dados.titulo());
        String idioma = String.join(", ", dados.idioma());
        livro.setIdioma(idioma);
        livro.setQuantidadeDownloads(Double.valueOf(dados.quantidadeDownloads()));
        livro.setAutores(autores);
        return livro;
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public void salvarLivro(Livro livro) {
        livroRepository.save(livro);
    }


    public List<Livro> encontrarLivroPorIdioma(String idioma) {
        return livroRepository.encontrarLivroPorIdioma(idioma);
    }
}
