package br.com.fp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fp.exception.AplicacaoException;
import br.com.fp.model.Resultado;

public class AnaliseDadosServiceTest {
	
	private AnaliseDadosService service;
	
	@BeforeEach
	public void setUp() {
		service = new AnaliseDadosService();
	}
	
	@Test
	public void deveSerPossivelProcessarAsLinhasDeUmArquivo() throws AplicacaoException {
		List<String> linhas = Arrays.asList(
				"001ç1234567891234çPedroç50000", 
				"001ç3245678865434çPauloç40000.99",
				"002ç2345675434544345çJose da SilvaçRural",
				"002ç2345675433444345çEduardo PereiraçRural",
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
				"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
		
		assertEquals(new Resultado(2, 2, 10, "Paulo"), service.processarLinhas(linhas));
	}
	
	@Test
	public void deveSerRetornarUmaExceptionQuandoOcorrerAlgumErroAoProcessarAsLinhas() throws AplicacaoException {
		List<String> linhas = Arrays.asList(
				"001ç1234567891234çPedroç50000", 
				"001ç3245678865434çPaulo",
				"002ç2345675434544345çJose da SilvaçRural",
				"002ç2345675433444345çEduardo PereiraçRural",
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
				"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
		
		Assertions.assertThrows(AplicacaoException.class, () -> {
			service.processarLinhas(linhas);
		});
		
	}
}
