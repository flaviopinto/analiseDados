package br.com.fp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.fp.constantes.Constantes;
import br.com.fp.util.Util;

public class Resultado extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer totalClientes;
	private Integer totalVendedores;
	private Venda maiorVenda;
	private Map<String, BigDecimal> mapaVendedorValor;
	
	public Resultado() {
		super();
	}

	public Resultado(Integer totalClientes, Integer totalVendedores, Venda maiorVenda,
			Map<String, BigDecimal> mapaVendedorValor) {
		super();
		this.totalClientes = totalClientes;
		this.totalVendedores = totalVendedores;
		this.maiorVenda = maiorVenda;
		this.mapaVendedorValor = mapaVendedorValor;
	}

	public Integer getTotalClientes() {
		return totalClientes;
	}

	public Integer getTotalVendedores() {
		return totalVendedores;
	}

	public Venda getMaiorVenda() {
		return maiorVenda;
	}
	
	public String getIdMaiorVenda() {
		if(getMaiorVenda() == null) {
			return Constantes.NAO_IDENTIFICADO;
		}
		return getMaiorVenda().getId().toString();
	}
	
	public String getPiorVendedor() {
		if (mapaVendedorValor == null) {
			return Constantes.NAO_IDENTIFICADO;
		}
		return Collections.min(mapaVendedorValor.entrySet(), Comparator.comparing(Entry::getValue)).getKey();
	}

	public void incrementarVendedor() {
		if(totalVendedores == null) {
			totalVendedores = 0;
		}
		totalVendedores++;
	}
	
	public void incrementarCliente() {
		if(totalClientes == null) {
			totalClientes = 0;
		}
		totalClientes++;
	}
	
	public void processarVenda(Venda venda) {
		manterMaiorVenda(venda);
		preencherMapaValorPorVendedor(venda);
	}
	
	private void manterMaiorVenda(Venda venda) {
		if (Util.verificarMaiorVenda(maiorVenda, venda)) {
			maiorVenda = venda;
		}
	}
	
	private void preencherMapaValorPorVendedor(Venda venda) {
		if (mapaVendedorValor == null) {
			mapaVendedorValor = new HashMap<String, BigDecimal>();
		}
		
		if(mapaVendedorValor.containsKey(venda.getNomeVendedor())) {
			mapaVendedorValor.put(venda.getNomeVendedor(), mapaVendedorValor.get(venda.getNomeVendedor()).add(venda.getTotalVenda()));
		} else {
			mapaVendedorValor.put(venda.getNomeVendedor(), venda.getTotalVenda());
		}
	}
	
	public String getArquivoProcessado() {
		StringBuilder retorno = new StringBuilder();
		retorno.append("Resultado: Total Clientes = ");
		retorno.append(getTotalClientes() == null ? Constantes.NAO_IDENTIFICADO : getTotalClientes());
		retorno.append(" Total Vendedores = ");
		retorno.append(getTotalVendedores() == null ? Constantes.NAO_IDENTIFICADO : getTotalVendedores());
		retorno.append(" Id Maior Venda = ");
		retorno.append(getIdMaiorVenda());
		retorno.append(" Pior Vendedor = ");
		retorno.append(getPiorVendedor());
		return  retorno.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapaVendedorValor == null) ? 0 : mapaVendedorValor.hashCode());
		result = prime * result + ((maiorVenda == null) ? 0 : maiorVenda.hashCode());
		result = prime * result + ((totalClientes == null) ? 0 : totalClientes.hashCode());
		result = prime * result + ((totalVendedores == null) ? 0 : totalVendedores.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resultado other = (Resultado) obj;
		if (mapaVendedorValor == null) {
			if (other.mapaVendedorValor != null)
				return false;
		} else if (!mapaVendedorValor.equals(other.mapaVendedorValor))
			return false;
		if (maiorVenda == null) {
			if (other.maiorVenda != null)
				return false;
		} else if (!maiorVenda.equals(other.maiorVenda))
			return false;
		if (totalClientes == null) {
			if (other.totalClientes != null)
				return false;
		} else if (!totalClientes.equals(other.totalClientes))
			return false;
		if (totalVendedores == null) {
			if (other.totalVendedores != null)
				return false;
		} else if (!totalVendedores.equals(other.totalVendedores))
			return false;
		return true;
	}

}
