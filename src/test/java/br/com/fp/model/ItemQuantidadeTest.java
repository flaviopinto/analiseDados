package br.com.fp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ItemQuantidadeTest {

	private ItemQuantidade itemQuantidade;
	
	@Test
	public void deveSerPossivelObterOValorTotalPorItem() {
		itemQuantidade = new ItemQuantidade(new Item(1, BigDecimal.valueOf(25)), 5);
		
		assertEquals(BigDecimal.valueOf(125), itemQuantidade.getTotalPorItem());
	}
}
