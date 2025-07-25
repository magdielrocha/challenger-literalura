package br.com.alura.challenger_literalura.service;

import br.com.alura.challenger_literalura.model.DadosLivro;
import br.com.alura.challenger_literalura.model.DadosResultadosLivro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverteDados implements IConverteDados {

    ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter json para " + classe.getSimpleName(), e);
        }
    }

    @Override
    public DadosLivro obterLivro(String json){
        try {
            DadosResultadosLivro resultado = mapper.readValue(json, DadosResultadosLivro.class);
            if (resultado.livros() != null && !resultado.livros().isEmpty()) {
                return resultado.livros().get(0);
            } else {
                throw new RuntimeException("\nNenhum livro encontrado. Tente novamente.\n");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter json para " + json, e);
        }
    }

}
