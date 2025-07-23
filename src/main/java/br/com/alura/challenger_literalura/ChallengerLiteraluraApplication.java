package br.com.alura.challenger_literalura;

import br.com.alura.challenger_literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengerLiteraluraApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ChallengerLiteraluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
