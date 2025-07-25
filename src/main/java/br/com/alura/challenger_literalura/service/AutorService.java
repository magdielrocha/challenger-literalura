package br.com.alura.challenger_literalura.service;

import br.com.alura.challenger_literalura.model.Autor;
import br.com.alura.challenger_literalura.model.DadosAutor;
import br.com.alura.challenger_literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public Autor salvarAutor(Autor autor) {
        Optional<Autor> autorExistente = autorRepository.findByNome(autor.getNome());

        if (autorExistente.isPresent()) {
            return autorExistente.get();
        }
        return autorRepository.save(autor);
    }

    public List<Autor> listarAutoresVivos(Integer ano){
        return autorRepository.listarAutoresVivos(ano);
    }

}
