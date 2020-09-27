package br.com.fp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.fp.model.Cliente;
import br.com.fp.model.Item;
import br.com.fp.model.ItemQuantidade;
import br.com.fp.model.Venda;
import br.com.fp.model.Vendedor;

public class UtilTest {
	
	@Test
	public void deveSerPossivelPreencherUmVendedor() {
		String linha = "1234567891234çPeçadriçoç50000";
		Vendedor vendedor = Util.preencherVendedor(linha, "ç");
		
		assertEquals("1234567891234", vendedor.getCpf());
		assertEquals("Peçadriço", vendedor.getNome());
		assertEquals(BigDecimal.valueOf(50000), vendedor.getSalario());
	}
	
	@Test
	public void deveSerPossivelPreencherUmCliente() {
		String linha = "2345675434544345çJose da SilvaçRural";
		Cliente cliente = Util.preencherCliente(linha, "ç");
		
		assertEquals("2345675434544345", cliente.getCnpj());
		assertEquals("Jose da Silva", cliente.getNome());
		assertEquals("Rural", cliente.getArea());
	}
	
	@Test
	public void deveSerPossivelSelecionarOTipoDeDados() {
		String linha1 = "001ç1234567891234çPeçadriçoç50000";
		String linha2 = "002ç2345675434544345çJose da SilvaçRural";
		String linha3 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
		
		String[] vetor1 = Util.identificarTipoDadosPorCaractere(linha1, "ç");
		String[] vetor2 = Util.identificarTipoDadosPorCaractere(linha2, "ç");
		String[] vetor3 = Util.identificarTipoDadosPorCaractere(linha3, "ç");
		
		assertEquals("001", vetor1[0]);
		assertEquals("002", vetor2[0]);
		assertEquals("003", vetor3[0]);
	}
	
	@Test
	public void deveSerPossivelPreencherUmItemQuantidade() {
		String linha = "1-34-10";
		ItemQuantidade itemQuantidade = Util.preencherItemQuantidade(linha, "-");
		
		assertEquals(new Item(1, BigDecimal.valueOf(10)), itemQuantidade.getItem());
		assertEquals(Integer.valueOf(34), itemQuantidade.getQuantidade());
	}
	
	@Test
	public void deveSerPossivelPreencherUmaListaDeItensQuantidade() {
		String linha = "1-34-10,2-33-1.50,3-40-0.10";
		List<ItemQuantidade> itens = Util.preencherListaItensQuantidade(linha, ",", "-");
		
		assertEquals(3, itens.size());
		assertEquals(Arrays.asList(
				new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 34), 
				new ItemQuantidade(new Item(2, BigDecimal.valueOf(1.50)), 33),
				new ItemQuantidade(new Item(3, BigDecimal.valueOf(0.10)), 40)), itens);
	}
	
	@Test
	public void deveSerPossivelRemoverColchetesDasExtremidadesDeUmaString() {
		String linha = "[1-34-10,2-33-1.50,3-40-0.10]";
		String linhaSemColchetes = Util.removerColchetesDasExtremidades(linha);
		
		assertEquals("1-34-10,2-33-1.50,3-40-0.10", linhaSemColchetes);
	}
	
	@Test
	public void deveSerPossivelPreencherUmaVenda() {
		String linha = "08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		Venda venda = Util.preencherVenda(linha, "ç", ",", "-");
		
		Venda vendaEsperada = new Venda(Integer.valueOf(8), 
				Arrays.asList(
						new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 34), 
						new ItemQuantidade(new Item(2, BigDecimal.valueOf(1.50)), 33),
						new ItemQuantidade(new Item(3, BigDecimal.valueOf(0.10)), 40)), 
				"Paulo");
		
		
		assertEquals(vendaEsperada, venda);
	}
	
	@Test
	public void deveSerPossivelObterAVendaMaisCara() {
		Venda vendaA = new Venda(1, 
				Arrays.asList(
						new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 1), 
						new ItemQuantidade(new Item(2, BigDecimal.valueOf(100)), 1),
						new ItemQuantidade(new Item(3, BigDecimal.valueOf(1000)), 1)), 
				"A");
		
		Venda vendaB = new Venda(3, 
				Arrays.asList(
						new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 3), 
						new ItemQuantidade(new Item(2, BigDecimal.valueOf(100)), 3),
						new ItemQuantidade(new Item(3, BigDecimal.valueOf(1000)), 3)), 
				"B");
		
		Venda vendaC = new Venda(2, 
				Arrays.asList(
						new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 2), 
						new ItemQuantidade(new Item(2, BigDecimal.valueOf(100)), 2),
						new ItemQuantidade(new Item(3, BigDecimal.valueOf(1000)), 2)), 
				"C");
		
		
		
		assertEquals(vendaB, Util.obterMenorVenda(Arrays.asList(vendaA, vendaB, vendaC)));
	}
	
	@Test
	public void deveSerPossivelVerificarSeUmaVendaEMenorQueAOutra() {
		Venda vendaA = new Venda(1, 
				Arrays.asList(
						new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 1), 
						new ItemQuantidade(new Item(2, BigDecimal.valueOf(100)), 1),
						new ItemQuantidade(new Item(3, BigDecimal.valueOf(1000)), 1)), 
				"A");
		
		Venda vendaB = new Venda(3, 
				Arrays.asList(
						new ItemQuantidade(new Item(1, BigDecimal.valueOf(10)), 3), 
						new ItemQuantidade(new Item(2, BigDecimal.valueOf(100)), 3),
						new ItemQuantidade(new Item(3, BigDecimal.valueOf(1000)), 3)), 
				"B");
		
		assertTrue(Util.verificarMenorVenda(null, vendaA));
		assertTrue(Util.verificarMenorVenda(vendaA, vendaB));
		assertFalse(Util.verificarMenorVenda(vendaB, vendaA));
	}
}
