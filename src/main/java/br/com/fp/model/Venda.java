package br.com.fp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Venda extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private List<ItemQuantidade> itens;
	private String nomeVendedor;
	
	public Venda(Integer id, List<ItemQuantidade> itens, String nomeVendedor) {
		super();
		this.id = id;
		this.itens = itens;
		this.nomeVendedor = nomeVendedor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<ItemQuantidade> getItens() {
		return itens;
	}
	public void setItens(List<ItemQuantidade> itens) {
		this.itens = itens;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	public BigDecimal getTotalVenda() {
		return itens.stream()
			.map(ItemQuantidade::getTotalPorItem)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((nomeVendedor == null) ? 0 : nomeVendedor.hashCode());
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (nomeVendedor == null) {
			if (other.nomeVendedor != null)
				return false;
		} else if (!nomeVendedor.equals(other.nomeVendedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", itens=" + itens + ", nomeVendedor=" + nomeVendedor + ", total=" + getTotalVenda() + "]";
	}
	
}
