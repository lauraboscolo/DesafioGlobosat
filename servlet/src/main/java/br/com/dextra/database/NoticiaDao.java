package br.com.dextra.database;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;

public class NoticiaDao {

	private EntityManager em;
	
	public NoticiaDao(EntityManager em) {
		this.em = em;
	}
	
	public boolean addNoticia(int idAssunto,String noticia,String link){
		Noticia n = new Noticia(idAssunto, noticia, link);
		
		// Abre a conexão com o Banco, insere um dado persistente
		em.getTransaction().begin();
		try{
			em.persist(n);
			// Commita o novo dado no banco
			em.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			// Desfazer o que foi feito
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
			// Remove a noticia do banco
			
			return n;
		}catch(Exception e){
			// Desfazer o que foi feito
			em.getTransaction().rollback();
			return null;
		}
	}

	
}
