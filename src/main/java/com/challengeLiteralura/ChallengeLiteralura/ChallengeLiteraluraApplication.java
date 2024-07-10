package com.challengeLiteralura.ChallengeLiteralura;

import com.challengeLiteralura.ChallengeLiteralura.principal.Principal;
import com.challengeLiteralura.ChallengeLiteralura.repository.AutoresRepositorio;
import com.challengeLiteralura.ChallengeLiteralura.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {


	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private AutoresRepositorio autoresRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(libroRepositorio, autoresRepositorio);
		principal.muestraMenu();

	}
}
