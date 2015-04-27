package br.com.dextra.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PerfilDao {

	private EntityManager em;

	public PerfilDao(EntityManager em) {
		this.em = em;
	}

	public boolean addPerfil(String descricao, List<Acesso> perfilAssuntos) {
		Perfil _perfil = new Perfil(descricao, perfilAssuntos);

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
		String stmt = "SELECT DISTINCT p FROM perfil p"
				+ " WHERE p.id = :id";
		System.out.println(stmt);
		Query query = em.createQuery(stmt);
		query.setParameter("id", id_perfil);
		return (Perfil) query.getSingleResult();
	}

	public Perfil removePerfilAssunto(int id) {
		try {
			Perfil perfil = this.getPerfil(id);
			em.remove(perfil);

			return perfil;
		} catch (Exception e) {
			return null;
		}
	}
}
