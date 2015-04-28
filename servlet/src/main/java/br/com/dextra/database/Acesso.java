package br.com.dextra.database;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "acesso")
public class Acesso implements Serializable{
	private static final long serialVersionUID = 9031154305004011711L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int perfil;
	
	@Column(nullable = false , name="assunto")
	private int assunto;
	
	@Column(nullable = false, name="qtdAcesso")
	private BigDecimal qtdAcesso;

	public Acesso(int p, int a, BigDecimal qtdAcessos){
		this.perfil = p;
		this.assunto = a;
		this.qtdAcesso = qtdAcessos;
		
	}
	
	public Acesso() {
	}

	public int getPerfil() {
		return perfil;
	}

	public int getAssunto() {
		return assunto;
	}

	public BigDecimal getQtdAcesso() {
		return qtdAcesso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public void setAssunto(int assunto) {
		this.assunto = assunto;
	}

	public void setQtdAcesso(BigDecimal qtdAcesso) {
		this.qtdAcesso = qtdAcesso;
	}
}
