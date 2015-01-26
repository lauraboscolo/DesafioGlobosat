package br.com.dextra.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="noticias")
public class Noticia {
	// Classe que representa a tabela Noticias do banco

	// Campos da Tabela
	@Id
	@Column(nullable=false,name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNoticia;
	
//	@ManyToOne
	@Column(nullable=false,name="id_assunto")
	private int idAssunto;

	@Column(nullable=false,name="link")
	private String link;
	
	@Column(nullable=false,name="noticia")
	private String noticia;
	
	public Noticia() {
		this.idAssunto = -1;
		this.noticia = "";
		this.link = "";
	}
	
	public Noticia(int idAssunto,String link,String noticia){
		if (idAssunto < 0 )
			throw new IllegalArgumentException("Campo 'idAssunto' é menor do que 0");
			
		if (noticia.isEmpty() || noticia == null)
			throw new IllegalArgumentException("Campo 'noticia' está vazio.");
		
		if (link.isEmpty() || link == null)
			throw new IllegalArgumentException("Campo 'link' está vazio.");
		
		this.idAssunto = idAssunto;
		this.noticia = noticia;
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

	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		if (noticia.isEmpty() || noticia == null)
			throw new IllegalArgumentException("Campo 'noticia' está vazio.");
		this.noticia = noticia;
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
