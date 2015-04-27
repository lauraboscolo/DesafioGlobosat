package br.com.dextra.database;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AcessoDao {

	private EntityManager em;

	public AcessoDao(EntityManager em) {
		this.em = em;
	}

	public boolean addAcesso(int idAssunto, int idPerfil, int qtdAcesso) {
		Acesso a = new Acesso(idPerfil,
				idAssunto, new BigDecimal(qtdAcesso));

		em.getTransaction().begin();
		try {
			em.persist(a);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		}
	}

	public Acesso getAcesso(int idAcesso) {
		return em.find(Acesso.class, idAcesso);
	}

	public Acesso removeAcesso(int idAcesso) {
		em.getTransaction().begin();
		try {
			Acesso a = this.getAcesso(idAcesso);
			em.remove(a);
			em.getTransaction().commit();
			return a;
		} catch (Exception e) {
			em.getTransaction().rollback();
			return null;
		}
	}
	
	public Acesso getAcessoPorAssuntoEPerfil(int idAssunto,int idPerfil){
		String stmt = "SELECT DISTINCT a FROM acesso a"
				+ " WHERE a.perfil = :idPerfil AND a.assunto = :idAssunto";
		Query query = em.createQuery(stmt);
		query.setParameter("idPerfil", idAssunto);
		query.setParameter("idAssunto", idPerfil);
		return (Acesso) query.getSingleResult();
	}

	public Acesso adicionarAcesso(int idAssunto,int idPerfil) {
		Acesso acesso = getAcessoPorAssuntoEPerfil(idAssunto, idPerfil);
		acesso.getQtdAcesso().add(BigDecimal.ONE);
		return em.merge(acesso);
	}

}
