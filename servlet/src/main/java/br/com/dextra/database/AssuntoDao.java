package br.com.dextra.database;

import javax.persistence.EntityManager;

public class AssuntoDao {

	private EntityManager em;
	
	public AssuntoDao(EntityManager em) {
		this.em = em;
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
		em.merge(n); **Lura adicionou esse treco - temos que testar**
		**/
		return false;
	} 
	
}
