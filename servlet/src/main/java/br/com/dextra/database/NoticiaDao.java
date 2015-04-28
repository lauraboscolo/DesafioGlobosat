package br.com.dextra.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NoticiaDao {

	private EntityManager em;

	public NoticiaDao(EntityManager em) {
		this.em = em;
	}

	public boolean addNoticia(int idAssunto,String noticia,String link){
		Noticia n = new Noticia(idAssunto, noticia, link);

		em.getTransaction().begin();
		try{
			em.persist(n);
			em.getTransaction().commit();

			return true;
		}catch(Exception e){
			em.getTransaction().rollback();
			return false;
		}
	}

	public Noticia getNoticia(int idNoticia){
		return em.find(Noticia.class, idNoticia);
	}

	public Noticia removeNoticia(int idNoticia){
		em.getTransaction().begin();
		try{
			Noticia n = this.getNoticia(idNoticia);
			em.remove(n);
			em.getTransaction().commit();
			return n;
		}catch(Exception e){
			em.getTransaction().rollback();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Noticia> getAlgumasNoticas(int quantas){
		String stmt = "SELECT * FROM noticia LIMIT :quantas";
		Query query = em.createNativeQuery(stmt, Noticia.class);
		query.setParameter("quantas", quantas);
		return query.getResultList();
	}

}
