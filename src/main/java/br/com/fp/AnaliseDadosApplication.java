package br.com.fp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnaliseDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnaliseDadosApplication.class);
	}

}
