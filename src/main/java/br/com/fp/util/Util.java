package br.com.fp.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import br.com.fp.model.Cliente;
import br.com.fp.model.Item;
import br.com.fp.model.ItemQuantidade;
import br.com.fp.model.Venda;
import br.com.fp.model.Vendedor;

public class Util {
	
	public static Vendedor preencherVendedor(String linha, String string) {
		String[] vetorObterCpf = linha.split(string, 2);
		String[] vetorObterNome = vetorObterCpf[1].split(string);
		
		if(vetorObterNome.length == 2) {
			return new Vendedor(vetorObterCpf[0], vetorObterNome[0], new BigDecimal(vetorObterNome[1]));
		}
		
		return new Vendedor(vetorObterCpf[0], 
				obterNome(string, vetorObterNome), 
				new BigDecimal(vetorObterNome[vetorObterNome.length - 1]));
	}
	
	public static Cliente preencherCliente(String linha, String caractere) {
		String[] vetorObterCnpj = linha.split(caractere, 2);
		String[] vetorObterNome = vetorObterCnpj[1].split(caractere);
		
		if(vetorObterNome.length == 2) {
			return new Cliente(vetorObterCnpj[0], vetorObterNome[0], vetorObterNome[1]);
		}
		
		return new Cliente(vetorObterCnpj[0], 
				obterNome(caractere, vetorObterNome), 
				vetorObterNome[vetorObterNome.length - 1]);
	}

	private static String obterNome(String string, String[] vetorObterNome) {
		String nome = vetorObterNome[0];
		for (int i = 1; i < vetorObterNome.length - 1; i++) {
			nome += string + vetorObterNome[i];
		}
		return nome;
	}
	
	public static String[] identificarTipoDadosPorCaractere(String linha, String caractere) {
		return linha.split(caractere, 2);
	}
	
	public static ItemQuantidade preencherItemQuantidade(String linha, String caractere) {
		String[] vetorObterItem = linha.split(caractere);
		return new ItemQuantidade(
				new Item(Integer.parseInt(vetorObterItem[0]), new BigDecimal(vetorObterItem[2])), 
				Integer.valueOf(vetorObterItem[1]));
	}

	public static String removerColchetesDasExtremidades(String linha) {
		return linha.replaceAll("\\[|\\]", "");
	}
	
	public static List<ItemQuantidade> preencherListaItensQuantidade(String linha, String caractereLista, String caractereItem) {
		List<ItemQuantidade> itens = new ArrayList<ItemQuantidade>();
		String[] vetorObterItens = linha.split(caractereLista);
		for (String itemString : vetorObterItens) {
			itens.add(preencherItemQuantidade(itemString, caractereItem));
		}
		return itens;
	}

	public static Venda preencherVenda(String linha, String caractereLinha, String caractereLista, String caractereItem) {
		String[] vetorObterVenda = linha.split(caractereLinha, 3);
		
		return new Venda(Integer.valueOf(vetorObterVenda[0]), 
				preencherListaItensQuantidade(removerColchetesDasExtremidades(vetorObterVenda[1]), caractereLista, caractereItem), 
				vetorObterVenda[2]);
	}

	public static Venda obterMenorVenda(List<Venda> vendas) {
		return vendas.stream()
		.max(Comparator.comparing(Venda::getTotalVenda))
		.orElseThrow(NoSuchElementException::new);
	}
	
	public static boolean verificarMenorVenda(Venda menorVenda, Venda venda) {
		return menorVenda == null || venda.getTotalVenda().compareTo(menorVenda.getTotalVenda()) > 0;
	}
}
