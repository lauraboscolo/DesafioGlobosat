package br.com.dextra.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AcessoDao {

	private EntityManager em;

	public AcessoDao(EntityManager em) {
		this.em = em;
	}

	public Acesso adicionarAcesso(int idAssunto, int idPerfil, BigDecimal qtdAcesso) {
		Acesso a = new Acesso(idPerfil, idAssunto, qtdAcesso);

		em.getTransaction().begin();
		try {
			em.persist(a);
			em.getTransaction().commit();
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
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

	public Acesso getAcessoPorAssuntoEPerfil(int idAssunto, int idUsuario) {
		String stmt = "SELECT DISTINCT a FROM acesso a"
				+ " WHERE a.idUsuario = :idUsuario AND a.assunto = :idAssunto";
		Query query = em.createQuery(stmt);
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("idAssunto", idAssunto);
		return (Acesso) query.getSingleResult();
	}

	public Acesso adicionarAcesso(int idAssunto, int idPerfil) {
		Acesso acesso;
		try {
			System.out.println("ASSUNTO :" + idAssunto);
			System.out.println("PERFIL :" + idPerfil);
			acesso = getAcessoPorAssuntoEPerfil(idAssunto, idPerfil);
			em.getTransaction().begin();
			acesso.setQtdAcesso(acesso.getQtdAcesso().add(BigDecimal.ONE));
			acesso = em.merge(acesso);
			em.getTransaction().commit();
		} catch (NoResultException e) {
			acesso = adicionarAcesso(idAssunto, idPerfil, BigDecimal.ONE);
		}
		return acesso;
	}

	public Map<Integer,List<Acesso>> getTodosAcessos(){
		StringBuilder stmt = new StringBuilder();
		stmt.append("FROM acesso order by qtdAcesso");
		
		Query query = em.createQuery(stmt.toString());
		@SuppressWarnings("unchecked")
		List<Acesso> result = query.getResultList();
		
		Map<Integer, List<Acesso>> map = new HashMap<>();
		for (Acesso acesso : result) {
			List<Acesso> list = map.get(acesso.getIdUsuario());
			if (list == null) {
				list = new ArrayList<>();
				map.put(acesso.getIdUsuario(), list);
			}
			list.add(acesso);
		}
		return map;
	}
	
}
