package br.com.dextra.database;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "usuario")
@Embeddable
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -9064070611033978682L;

	@Id
	@Column(nullable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, name = "qtosUsuarios")
	private BigDecimal qtosUsuarios;

	@SuppressWarnings("unused")
	private Usuario(int id, BigDecimal qtosUsuarios) {
		this.id = id;
		this.setQtosUsuarios(qtosUsuarios);
	}

	public Usuario() {}

	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	public BigDecimal getQtosUsuarios() {
		return qtosUsuarios;
	}

	public void setQtosUsuarios(BigDecimal qtosUsuarios) {
		this.qtosUsuarios = qtosUsuarios;
	}
}
