package br.com.fp.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemQuantidade extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Item item;
	private Integer quantidade;
	
	public ItemQuantidade(Item item, Integer quantidade) {
		super();
		this.item = item;
		this.quantidade = quantidade;
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getTotalPorItem() {
		return item.getPreco().multiply(BigDecimal.valueOf(quantidade));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		ItemQuantidade other = (ItemQuantidade) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
	
}
