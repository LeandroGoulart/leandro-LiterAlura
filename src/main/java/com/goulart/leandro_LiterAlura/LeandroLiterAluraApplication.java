package com.goulart.leandro_LiterAlura;

import com.goulart.leandro_LiterAlura.repository.RepositorioAutor;
import com.goulart.leandro_LiterAlura.repository.RepositorioLivro;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class LeandroLiterAluraApplication implements CommandLineRunner {

	@Autowired
	RepositorioAutor autorRepository;
	@Autowired
	RepositorioLivro repositorioLivro;

	public static void main(String[] args) {
		SpringApplication.run(LeandroLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(RepositorioAutor, repositorioLivro);
		Principal.menu();
	}
}
