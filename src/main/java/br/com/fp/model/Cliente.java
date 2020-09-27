package br.com.fp.model;

import java.io.Serializable;

public class Cliente extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cnpj;
	private String nome;
	private String area;
	
	public Cliente(String cnpj, String nome, String area) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.area = area;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	
}
