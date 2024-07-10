package com.goulart.literalura;


import com.goulart.literalura.repository.AutorRepository;
import com.goulart.literalura.repository.LivroRepository;
import com.goulart.literalura.service.ConverteLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private ConverteLivro converteLivroService;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorRepository, livroRepository, converteLivroService);
		principal.menu();
	}
}
