package br.com.alura.challenger_literalura_2;

import br.com.alura.challenger_literalura_2.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengerLiteralura2Application implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ChallengerLiteralura2Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
