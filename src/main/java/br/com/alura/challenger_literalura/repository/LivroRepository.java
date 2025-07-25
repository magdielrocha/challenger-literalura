package br.com.alura.challenger_literalura.repository;

import br.com.alura.challenger_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository  extends JpaRepository<Livro, Long> {

}
