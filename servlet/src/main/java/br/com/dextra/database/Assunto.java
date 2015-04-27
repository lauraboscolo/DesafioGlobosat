package br.com.dextra.database;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="assunto")
@Embeddable
public class Assunto implements java.io.Serializable {
	
	private static final long serialVersionUID = -5304231939338469956L;

	@Id
	@Column(nullable=false,name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAssunto;
	
	@Column(nullable=false,name="assunto")
	private String assunto;
	
	public Assunto() {
	}
	public Assunto(String assunto){
		if (assunto.isEmpty() || assunto == null)
			throw new IllegalArgumentException("Campo Assunto Vazio");
		this.assunto = assunto;
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
}
