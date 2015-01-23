package br.com.dextra.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class NoticiaDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public NoticiaDao(){
		emf = Persistence.createEntityManagerFactory("DesafioGlobosat");
		em = emf.createEntityManager();
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

	public boolean updateNoticia(int idNoticia,int idAssunto,String noticia,String link){
		/** IMPLEMENTAR
		em.getTransaction().begin();
		Noticia n = getNoticia(idNoticia);
		n.setIdAssunto(idAssunto);
		n.setLink(link);
		n.setNoticia(noticia);	
		**/
		return false;
	} 

}
