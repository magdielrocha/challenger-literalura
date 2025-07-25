package br.com.alura.challenger_literalura.service;


import br.com.alura.challenger_literalura.model.DadosLivro;

public interface IConverteDados {

    <T> T  obterDados(String json, Class<T> classe);

    DadosLivro obterLivro(String json);

}
