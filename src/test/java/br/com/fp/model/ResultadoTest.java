package br.com.fp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultadoTest {
	
	private Resultado resultado;
	
	@BeforeEach
	public void setUp() {
		resultado = new Resultado();
	}
	
	@Test
	public void deveSerPossivelIncrementarOPrimeiroVendedor() {
		resultado.incrementarVendedor();
		
		assertEquals(1, resultado.getTotalVendedores());
	}
	
	@Test
	public void deveSerPossivelIncrementarUmVendedorComVendedorAdicionadoAnteriormente() {
		resultado.incrementarVendedor();
		resultado.incrementarVendedor();
		
		assertEquals(2, resultado.getTotalVendedores());
	}
	
	@Test
	public void deveSerPossivelIncrementarOPrimeiroCliente() {
		resultado.incrementarCliente();
		
		assertEquals(1, resultado.getTotalClientes());
	}
	
	@Test
	public void deveSerPossivelIncrementarUmClienteComClienteAdicionadoAnteriormente() {
		resultado.incrementarCliente();
		resultado.incrementarCliente();
		
		assertEquals(2, resultado.getTotalClientes());
	}
	
	@Test
	public void deveSerPossivelPreencherAMenorVendaNaPrimeiraVendaAdicionada() {
		Venda venda = new Venda(1, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(25)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Teste");
		resultado.processarVenda(venda);
		
		assertEquals(venda, resultado.getMaiorVenda());
	}
	
	@Test
	public void deveSerPossivelPreencherAMenorVendaComUmaVendaAnteriomenteAdicionada() {
		Venda vendaA = new Venda(1, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Teste A");
		Venda vendaB = new Venda(2, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(30)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Teste B");
		Venda vendaC = new Venda(3, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(20)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Teste C");
		
		resultado.processarVenda(vendaA);
		resultado.processarVenda(vendaB);
		resultado.processarVenda(vendaC);
		
		assertEquals(vendaB, resultado.getMaiorVenda());
	}
	
	@Test
	public void deveSerPossivelObterOPiorVendedor() {
		Venda vendaA = new Venda(1, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Vendedor A");
		Venda vendaB = new Venda(2, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(30)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Vendedor B");
		Venda vendaC = new Venda(3, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(20)), 5), new ItemQuantidade(new Item(1, BigDecimal.valueOf(13.30)), 3)), 
				"Vendedor C");
		Venda vendaD = new Venda(3, 
				Arrays.asList(new ItemQuantidade(new Item(1, BigDecimal.valueOf(1)), 1)), 
				"Vendedor A");
		
		resultado.processarVenda(vendaA);
		resultado.processarVenda(vendaB);
		resultado.processarVenda(vendaC);
		resultado.processarVenda(vendaD);
		
		assertEquals("Vendedor A", resultado.getPiorVendedor());
	}
	
}
