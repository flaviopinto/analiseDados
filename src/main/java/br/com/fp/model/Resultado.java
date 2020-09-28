package br.com.fp.model;

import java.io.Serializable;

public class Resultado extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer quantidadeClientes;
	private Integer quantidadeVendedores;
	private Integer idMaiorVenda;
	private String nomePiorVendedor;
	
	public Resultado(Integer quantidadeClientes, Integer quantidadeVendedores, Integer idMaiorVenda,
			String nomePiorVendedor) {
		super();
		this.quantidadeClientes = quantidadeClientes;
		this.quantidadeVendedores = quantidadeVendedores;
		this.idMaiorVenda = idMaiorVenda;
		this.nomePiorVendedor = nomePiorVendedor;
	}
	
	public Integer getQuantidadeClientes() {
		return quantidadeClientes;
	}
	public void setQuantidadeClientes(Integer quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}
	public Integer getQuantidadeVendedores() {
		return quantidadeVendedores;
	}
	public void setQuantidadeVendedores(Integer quantidadeVendedores) {
		this.quantidadeVendedores = quantidadeVendedores;
	}
	public Integer getIdMaiorVenda() {
		return idMaiorVenda;
	}
	public void setIdMaiorVenda(Integer idMaiorVenda) {
		this.idMaiorVenda = idMaiorVenda;
	}
	public String getNomePiorVendedor() {
		return nomePiorVendedor;
	}
	public void setNomePiorVendedor(String nomePiorVendedor) {
		this.nomePiorVendedor = nomePiorVendedor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMaiorVenda == null) ? 0 : idMaiorVenda.hashCode());
		result = prime * result + ((nomePiorVendedor == null) ? 0 : nomePiorVendedor.hashCode());
		result = prime * result + ((quantidadeClientes == null) ? 0 : quantidadeClientes.hashCode());
		result = prime * result + ((quantidadeVendedores == null) ? 0 : quantidadeVendedores.hashCode());
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
		if (idMaiorVenda == null) {
			if (other.idMaiorVenda != null)
				return false;
		} else if (!idMaiorVenda.equals(other.idMaiorVenda))
			return false;
		if (nomePiorVendedor == null) {
			if (other.nomePiorVendedor != null)
				return false;
		} else if (!nomePiorVendedor.equals(other.nomePiorVendedor))
			return false;
		if (quantidadeClientes == null) {
			if (other.quantidadeClientes != null)
				return false;
		} else if (!quantidadeClientes.equals(other.quantidadeClientes))
			return false;
		if (quantidadeVendedores == null) {
			if (other.quantidadeVendedores != null)
				return false;
		} else if (!quantidadeVendedores.equals(other.quantidadeVendedores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resultado [quantidadeClientes=" + quantidadeClientes + ", quantidadeVendedores=" + quantidadeVendedores
				+ ", idMaiorVenda=" + idMaiorVenda + ", nomePiorVendedor=" + nomePiorVendedor + "]";
	}
		
}
