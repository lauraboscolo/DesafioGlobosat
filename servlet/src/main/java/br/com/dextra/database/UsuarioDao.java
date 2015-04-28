package br.com.dextra.database;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioDao {

	private EntityManager em;

	public UsuarioDao(EntityManager em) {
		this.em = em;
	}

	public Usuario getProxUsuario() {
		em.getTransaction().begin();

		Usuario usuario = getUsuario(1);
		usuario.setQtosUsuarios(usuario.getQtosUsuarios().add(BigDecimal.ONE));
		usuario = em.merge(usuario);

		em.getTransaction().commit();
		return usuario;
	}

	public Usuario getUsuario(int id) {
		return em.find(Usuario.class, id);
	}

	public Usuario getUsuario() {
		return em.find(Usuario.class, 1);
	}

	public Usuario getUsuario(BigDecimal idUsuario) {
		String stmt = "SELECT DISTINCT u FROM usuario u WHERE u.idUsuario = :idUsuario";
		Query query = em.createQuery(stmt);
		query.setParameter("idUsuario", idUsuario);
		return (Usuario) query.getSingleResult();
	}

	public Usuario removeUsuarioAssunto(int id) {
		try {
			Usuario usuario = this.getUsuario(id);
			em.remove(usuario);

			return usuario;
		} catch (Exception e) {
			return null;
		}
	}
}
