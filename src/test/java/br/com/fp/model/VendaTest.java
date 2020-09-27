package br.com.fp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class VendaTest {
	
	private Venda venda;
	
	@Test
	public void deveSerPossivelObterOValorTotalPorItem() {
		venda = new Venda(1, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(25)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Teste");
		
		assertEquals(BigDecimal.valueOf(164.90), venda.getTotalVenda());
	}
}
