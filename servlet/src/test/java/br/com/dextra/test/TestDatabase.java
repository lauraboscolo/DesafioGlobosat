package br.com.dextra.test;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextra.database.AssuntoDao;
import br.com.dextra.database.PerfilDao;

public class TestDatabase {
	
	@Test
	public void testPesquisaDeAssunto() throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("globosat");
		EntityManager em = emf.createEntityManager();
		
		AssuntoDao assuntos = new AssuntoDao(em);
		Assert.assertNotEquals(assuntos.getAssunto(1).getIdAssunto(),0);
		
		em.close();
		emf.close();
	}

	@Test
	public void testPesquisaDeAssuntoComIdPerfil() throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("globosat");
		EntityManager em = emf.createEntityManager();
		
		PerfilDao perfilAssunto = new PerfilDao(em);
		
		// idperfil = 1 ; correto = {1,7}
		Assert.assertNotNull(perfilAssunto.getPerfilAssuntoResolvendoLazy(1).getAssuntos());
		
		em.close();
		emf.close();
	}

}
