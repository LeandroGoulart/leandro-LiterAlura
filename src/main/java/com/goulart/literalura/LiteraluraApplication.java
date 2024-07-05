package com.goulart.literalura;


import com.goulart.literalura.dto.DadosLivro;
import com.goulart.literalura.service.ConsumoApi;
import com.goulart.literalura.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal.menu();



	}
}
