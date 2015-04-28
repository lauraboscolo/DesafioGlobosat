package br.com.dextra.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "perfil")
@Embeddable
public class Perfil implements java.io.Serializable {

	private static final long serialVersionUID = -9064070611033978682L;

	@Id
	@Column(nullable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, name = "nome")
	private String nome;

	@ManyToMany(targetEntity = Acesso.class)
	private List<Acesso> perfilAcessos;

	@SuppressWarnings("unused")
	private Perfil(int id, String nome) {
		this.id = id;
		this.nome = nome;
		this.perfilAcessos = new ArrayList<Acesso>();
	}

	public Perfil() {}

	public Perfil( String descricao, List<Acesso> perfilAssuntos) {
		this.nome = descricao;
		this.perfilAcessos = perfilAssuntos;
	}

	public String getDescricao() {
		return nome;
	}

	public void setDescricao(String descricao) {
		this.nome = descricao;
	}

	public List<Acesso> getPerfilAssunto() {
		return perfilAcessos;
	}

	public void setPerfilAssunto(List<Acesso> assuntos) {
		this.perfilAcessos = assuntos;
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}
}
