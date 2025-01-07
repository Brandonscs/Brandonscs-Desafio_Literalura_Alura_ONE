package com.brandonscs.desafio.literalura;

import com.brandonscs.desafio.literalura.principal.Principal;
import com.brandonscs.desafio.literalura.repository.AutorRepository;
import com.brandonscs.desafio.literalura.repository.BookRepository;
import com.brandonscs.desafio.literalura.service.ConsumoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private BookRepository repositorioLibro;
	@Autowired
	private AutorRepository repositorioAutor;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(repositorioLibro, repositorioAutor);
		principal.mostrarMenu();
	}
}
