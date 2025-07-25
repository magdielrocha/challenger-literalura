package br.com.alura.challenger_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultadosLivro(@JsonAlias("results") List<DadosLivro> livros) {
}
