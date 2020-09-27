package br.com.fp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import br.com.fp.constantes.Constantes;
import br.com.fp.exception.AplicacaoException;
import br.com.fp.model.Cliente;
import br.com.fp.model.Resultado;
import br.com.fp.model.Venda;
import br.com.fp.model.Vendedor;
import br.com.fp.util.Util;

@Component
public class AnaliseDadosService {

	public Resultado processarLinhas(List<String> linhas) throws AplicacaoException {
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		List<Cliente> clientes = new ArrayList<Cliente>();
		Map<String, BigDecimal> mapaVendedorValor = new HashMap<String, BigDecimal>();
		Venda menorVenda = null;
		
		try {
			for (String linha : linhas) {
				String[] vetorDados = Util.identificarTipoDadosPorCaractere(linha, Constantes.CTE_LINHA);
				
				if(Constantes.IDENTIFICADOR_VENDEDOR.equals(vetorDados[0])) {
					vendedores.add(Util.preencherVendedor(vetorDados[1], Constantes.CTE_LINHA));
				} else if (Constantes.IDENTIFICADOR_CLIENTE.equals(vetorDados[0])) {
					clientes.add(Util.preencherCliente(vetorDados[1], Constantes.CTE_LINHA));
				} else if (Constantes.IDENTIFICADOR_VENDA.equals(vetorDados[0])) {
					Venda venda = Util.preencherVenda(vetorDados[1], Constantes.CTE_LINHA, Constantes.CTE_LISTA, Constantes.CTE_ITEM);
					menorVenda = preencherMenorVenda(menorVenda, venda);
					preencherMapaValorPorVendedor(mapaVendedorValor, venda);
				}
			}
			
			return new Resultado(clientes.size(), 
					vendedores.size(), 
					menorVenda.getId(), 
					Collections.min(mapaVendedorValor.entrySet(), Comparator.comparing(Entry::getValue)).getKey());
			
		} catch (RuntimeException e) {
			throw new AplicacaoException("Erro ao processar linhas do arquivo: ");
		}
		
	}

	private Venda preencherMenorVenda(Venda menorVenda, Venda venda) {
		if (Util.verificarMenorVenda(menorVenda, venda)) {
			return venda;
		}
		return menorVenda;
	}

	private void preencherMapaValorPorVendedor(Map<String, BigDecimal> mapaVendedorValor, Venda venda) {
		if(mapaVendedorValor.containsKey(venda.getNomeVendedor())) {
			mapaVendedorValor.put(venda.getNomeVendedor(), mapaVendedorValor.get(venda.getNomeVendedor()).add(venda.getTotalVenda()));
		} else {
			mapaVendedorValor.put(venda.getNomeVendedor(), venda.getTotalVenda());
		}
	}

}
