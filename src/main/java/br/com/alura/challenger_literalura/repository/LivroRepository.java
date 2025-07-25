package br.com.alura.challenger_literalura.repository;

import br.com.alura.challenger_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository  extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma LIKE %:idioma%")
    List<Livro> encontrarLivroPorIdioma(@Param("idioma") String idioma);
}
