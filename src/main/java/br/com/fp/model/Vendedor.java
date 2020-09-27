package br.com.fp.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Vendedor extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String nome;
	private BigDecimal salario;
	
	public Vendedor(String cpf, String nome, BigDecimal salario) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	
}
