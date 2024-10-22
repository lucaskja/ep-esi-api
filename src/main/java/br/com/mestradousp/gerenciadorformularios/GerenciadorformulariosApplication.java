package br.com.mestradousp.gerenciadorformularios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GerenciadorformulariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorformulariosApplication.class, args);
	}

}
