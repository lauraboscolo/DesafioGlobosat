package br.com.dextra.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="assuntos")
public class Assunto {

	@Id
	@Column(nullable=false,name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAssunto;
	
	@Column(nullable=false,name="assunto")
	private String assunto;
//	
//	@OneToMany(mappedBy="idAssunto")
//	private List<Noticia> noticias;
	
	public Assunto() {
		this.assunto="";
		this.idAssunto=-1;
	}
	public Assunto(String assunto){
		if (assunto.isEmpty() || assunto == null)
			throw new IllegalArgumentException("Campo Assunto Vazio");
		this.assunto = assunto;
		this.idAssunto = -1;
	}

	public int getIdAssunto() {
		return idAssunto;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		if (assunto.isEmpty() || assunto == null)
			throw new IllegalArgumentException("Campo Assunto Vazio");
		this.assunto = assunto;
	}
	
		
//	public List<Noticia> getAssuntos() {
//		return noticias;
//	}
//
//	public void setAssuntos(List<Noticia> assuntos) {
//		this.noticias = assuntos;
//	}
	
}