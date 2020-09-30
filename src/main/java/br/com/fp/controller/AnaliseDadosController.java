package br.com.fp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.fp.constantes.Constantes;
import br.com.fp.exception.AplicacaoException;
import br.com.fp.model.Resultado;
import br.com.fp.service.AnaliseDadosService;

@Component
public class AnaliseDadosController {
	
	@Autowired
	private AnaliseDadosService service;

	@Scheduled(fixedDelay = Constantes.INTERVALO)
	public void processar() {

		try {
			criarDiretorios();

			pesquisarArquivos()
				.forEach(this::processarArquivo);
		
		} catch (IOException e) {
			System.out.println("Erro ao criar diretório");
			return;
		}
	}
	
	private Stream<File> pesquisarArquivos() {
		File file = new File(Constantes.PATH_ENTRADA.toString());
		
		return Arrays.stream(file.listFiles())
				.filter(f -> f.isFile());
	}

	private void criarDiretorios() throws IOException {
		Files.createDirectories(Constantes.PATH_ENTRADA);
		Files.createDirectories(Constantes.PATH_SAIDA);
	}
	
	private void processarArquivo(File file) {
		System.out.println("processando arquivo: " + file.getAbsolutePath());
		try {
			if (!Files.isReadable(Paths.get(file.getAbsolutePath()))) {
				throw new AplicacaoException("Erro ao ler o diretório: " + file.getAbsolutePath());
			}
			
			processarArquivoPorLinha(file);
		} catch (IOException e) {
			System.out.println("Erro ao processar arquivo: " + file.getAbsolutePath());
		} catch (AplicacaoException e) {
			System.out.println(e.getMessage() + file.getAbsolutePath());
		}
	}

	private void processarArquivoPorLinha(File file) throws IOException, AplicacaoException {
		Resultado resultado = new Resultado();
		try (Scanner scanner = new Scanner(new FileInputStream(file.getAbsoluteFile()))) {
		    while (scanner.hasNextLine()) {
		    	resultado = service.processarLinha(scanner.nextLine(), resultado);
		    }
		}
		
		Files.deleteIfExists(file.toPath());
		Files.write(Paths.get(Constantes.PATH_SAIDA + File.separator + file.getName()), 
				resultado.getArquivoProcessado().getBytes());
	}

}
