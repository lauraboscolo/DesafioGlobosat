package br.com.dextra.database;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "perfis")
public class Perfil implements java.io.Serializable {

	private static final long serialVersionUID = -9064070611033978682L;

	@Id
	@Column(nullable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, name = "nome")
	private String nome;

	@ManyToMany
	@JoinTable(name = "perfis_assuntos",
	joinColumns = { @JoinColumn(name = "id_perfil") },
	inverseJoinColumns = { @JoinColumn(name = "id_assunto") })
	private List<Assunto> assuntos;

	public Perfil() {
		this(-1, "");
	}
	
	public Perfil(int id,String descricao) {
		this.id = id;
		this.nome = descricao;
	}

	public String getDescricao() {
		return nome;
	}

	public void setDescricao(String descricao) {
		this.nome = descricao;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public int getId() {
		return id;
	}

}
