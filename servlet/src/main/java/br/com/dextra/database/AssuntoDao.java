package br.com.dextra.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AssuntoDao {

	private EntityManager em;
	
	public AssuntoDao(EntityManager em) {
		this.em = em;
	}
	
	public boolean addAssunto(String assunto){
		Assunto a = new Assunto(assunto);
		
		em.getTransaction().begin();
		try{
			em.persist(a);
			em.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			em.getTransaction().rollback();
			return false;
		}
	}

	public Assunto getAssunto(int idAssunto){
		return em.find(Assunto.class, idAssunto);
	}

	public Assunto removeAssunto(int idAssunto){
		em.getTransaction().begin();
		try{
			Assunto a = this.getAssunto(idAssunto);
			em.remove(a);
			em.getTransaction().commit();
			
			return a;
		}catch(Exception e){
			em.getTransaction().rollback();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Noticia> getNoticiaAssuntoResolvendoLazy(int id_assunto) {
		String stmt = "SELECT DISTINCT n FROM noticias n"
				+ " WHERE n.idAssunto = :id";
		System.out.println(stmt);
		Query query = em.createQuery(stmt);
		query.setParameter("id", id_assunto);
		return (List<Noticia>) query.getResultList();
	}
}
