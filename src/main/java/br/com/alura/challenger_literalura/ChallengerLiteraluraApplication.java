package br.com.alura.challenger_literalura;

import br.com.alura.challenger_literalura.principal.Principal;
import br.com.alura.challenger_literalura.repository.LivroRepository;
import br.com.alura.challenger_literalura.service.AutorService;
import br.com.alura.challenger_literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengerLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LivroService livroService;

    @Autowired
    private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengerLiteraluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroService, autorService);
		principal.exibeMenu();

	}
}
