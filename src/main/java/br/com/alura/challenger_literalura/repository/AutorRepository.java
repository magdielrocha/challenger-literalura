package br.com.alura.challenger_literalura.repository;

import br.com.alura.challenger_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository  extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano  AND (a.anoMorte IS NULL OR a.anoMorte > :ano)")
    List<Autor> listarAutoresVivos(@Param("ano") Integer ano);

}
