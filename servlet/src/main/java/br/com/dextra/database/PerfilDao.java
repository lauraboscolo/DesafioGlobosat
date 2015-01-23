package br.com.dextra.database;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PerfilDao {

	private EntityManager em;

	public PerfilDao(EntityManager em) {
		this.em = em;
	}

	public boolean addPerfil(int perfil) {
		Perfil _perfil = new Perfil(perfil, null);

		// Abre a conexão com o Banco, insere um dado persistente
		em.getTransaction().begin();
		try {
			em.persist(_perfil);
			// Commita o novo dado no banco
			em.getTransaction().commit();

			return true;
		} catch (Exception e) {
			// Desfazer o que foi feito
			em.getTransaction().rollback();
			return false;
		}
	}

	public Perfil getPerfil(int id) {
		return em.find(Perfil.class, id);
	}

	public Perfil getPerfilAssuntoResolvendoLazy(int id_perfil) {
		String stmt = "SELECT DISTINCT p FROM " + Perfil.class.getSimpleName()
				+ " JOIN FETCH p.assuntos " + " WHERE p.id = :id";
		Query query = em.createNativeQuery(stmt);
		query.setParameter("id", id_perfil);
		return (Perfil) query.getSingleResult();
	}

	public Perfil removePerfilAssunto(int id) {
		em.getTransaction().begin();
		try {
			Perfil perfil = this.getPerfil(id);
			em.remove(perfil);
			em.getTransaction().commit();
			// Remove a Assunto do banco

			return perfil;
		} catch (Exception e) {
			// Desfazer o que foi feito
			em.getTransaction().rollback();
			return null;
		}
	}
}
