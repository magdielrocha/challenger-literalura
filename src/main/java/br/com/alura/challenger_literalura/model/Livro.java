package br.com.alura.challenger_literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table (name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    private String idioma;

    private Double quantidadeDownloads;

    public Livro() {}

    public Livro(String titulo, String idioma, Double quantidadeDownloads, List<Autor> autores) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.quantidadeDownloads = quantidadeDownloads;
        this.autores = autores;
    }

//    public Livro(DadosLivro dados) {
//        this.titulo = dados.titulo();
//        this.idioma = String.join(", ", dados.idioma());
//        this.quantidadeDownloads = Double.valueOf(dados.quantidadeDownloads());
//
//        this.autores = dados.autores()
//                .stream()
//                .map(a -> autorService.salvarAutor(new Autor(a.nome(), a.anoNascimento(), a.anoMorte()))
//                        .collect(Collectors.toList());
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getQuantidadeDownloads() {
        return quantidadeDownloads;
    }

    public void setQuantidadeDownloads(Double quantidadeDownloads) {
        this.quantidadeDownloads = quantidadeDownloads;
    }

    @Override
    public String toString() {
        return "-----------------------------" +
                "\nTítulo: " + titulo +
                "\nAutor: " + autores.stream().map(Autor::getNome).collect(Collectors.joining(", ")) +
                "\nIdioma: " + idioma +
                "\nDownloads: " + quantidadeDownloads +
                "\n-----------------------------";
    }

}
