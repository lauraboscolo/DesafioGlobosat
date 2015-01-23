package br.com.dextra.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AssuntoDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public AssuntoDao(){
		emf = Persistence.createEntityManagerFactory("DesafioGlobosat");
		em = emf.createEntityManager();
	}
	
	public boolean addAssunto(String assunto){
		Assunto a = new Assunto(assunto);
		
		// Abre a conexão com o Banco, insere um dado persistente
		em.getTransaction().begin();
		try{
			em.persist(a);
			// Commita o novo dado no banco
			em.getTransaction().commit();
			
			return true;
		}catch(Exception e){
			// Desfazer o que foi feito
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
			// Remove a Assunto do banco
			
			return a;
		}catch(Exception e){
			// Desfazer o que foi feito
			em.getTransaction().rollback();
			return null;
		}
	}

	public boolean updateAssunto(int idAssunto,String Assunto){
		/** IMPLEMENTAR
		em.getTransaction().begin();
		Assunto n = getAssunto(idAssunto);
		n.setIdAssunto(idAssunto);
		n.setLink(link);
		n.setAssunto(Assunto);	
		**/
		return false;
	} 

}
