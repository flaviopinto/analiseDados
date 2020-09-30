package br.com.fp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fp.exception.AplicacaoException;
import br.com.fp.model.Resultado;
import br.com.fp.model.Venda;

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
		
		Venda vendaTeste = new Venda(10, Collections.emptyList(), null);
		Map<String, BigDecimal> mapaTeste = new HashMap<String, BigDecimal>();
		mapaTeste.put("Paulo", BigDecimal.TEN);
		
		assertEquals(new Resultado(2, 2, vendaTeste, mapaTeste).getArquivoProcessado(), service.processarLinhas(linhas).getArquivoProcessado());
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
	
	@Test
	public void deveSerPossivelProcessarAsLinhaDeUmArquivo() throws AplicacaoException {
		String linhaA = "001ç1234567891234çPedroç50000";
		String linhaB = "002ç2345675434544345çJose da SilvaçRural";
		String linhaC = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
		
		Venda vendaTeste = new Venda(10, Collections.emptyList(), null);
		Map<String, BigDecimal> mapaTeste = new HashMap<String, BigDecimal>();
		mapaTeste.put("Pedro", BigDecimal.TEN);
		
		assertEquals(new Resultado(null, 1, null, null).getArquivoProcessado(), service.processarLinha(linhaA, new Resultado()).getArquivoProcessado());
		assertEquals(new Resultado(1, null, null, null).getArquivoProcessado(), service.processarLinha(linhaB, new Resultado()).getArquivoProcessado());
		assertEquals(new Resultado(null, null, vendaTeste, mapaTeste).getArquivoProcessado(), service.processarLinha(linhaC, new Resultado()).getArquivoProcessado());
	}
	
	@Test
	public void deveSerRetornarUmaExceptionQuandoOcorrerAlgumErroAoProcessarAlgumaLinha() throws AplicacaoException {
		String linhaA = "001ç1234567891234çPedro";
		String linhaB = "002çJose da SilvaçRural";
		String linhaC = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]";
		
		Assertions.assertThrows(AplicacaoException.class, () -> {
			service.processarLinha(linhaA, new Resultado());
		});
		
		Assertions.assertThrows(AplicacaoException.class, () -> {
			service.processarLinha(linhaB, new Resultado());
		});
		
		Assertions.assertThrows(AplicacaoException.class, () -> {
			service.processarLinha(linhaC, new Resultado());
		});
		
	}
}
