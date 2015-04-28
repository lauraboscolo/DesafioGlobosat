package br.com.dextra.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="noticia")
public class Noticia implements java.io.Serializable {
	private static final long serialVersionUID = 7772366447603193317L;

	// Campos da Tabela
	@Id
	@Column(nullable=false,name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNoticia;

	@Column(nullable=false,name="id_assunto")
	private int idAssunto;

	@Column(nullable=false,name="link")
	private String link;

	@Column(nullable=false,name="titulo")
	private String titulo;

	public Noticia() {
		this.idAssunto = -1;
		this.titulo = "";
		this.link = "";
	}

	public Noticia(int idAssunto,String link,String titulo){
		if (idAssunto < 0 )
			throw new IllegalArgumentException("Campo 'idAssunto' é menor do que 0");

		if (titulo.isEmpty() || titulo == null)
			throw new IllegalArgumentException("Campo 'titulo' está vazio.");

		if (link.isEmpty() || link == null)
			throw new IllegalArgumentException("Campo 'link' está vazio.");

		this.idAssunto = idAssunto;
		this.titulo = titulo;
		this.link = link;
	}

	public int getIdNoticia() {
		return idNoticia;
	}

	public int getIdAssunto() {
		return idAssunto;
	}

	public void setIdAssunto(int idAssunto) {
		if (idAssunto < 0 )
			throw new IllegalArgumentException("Campo 'idAssunto' é menor do que 0");
		this.idAssunto = idAssunto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo.isEmpty() || titulo == null)
			throw new IllegalArgumentException("Campo 'titulo' está vazio.");
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		if (link.isEmpty() || link == null)
			throw new IllegalArgumentException("Campo 'link' está vazio.");
		this.link = link;
	}
}
