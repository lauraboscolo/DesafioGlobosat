package br.com.dextra.test;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.dextra.database.AssuntoDao;
import br.com.dextra.database.PerfilDao;

public class TestDatabase {
	
	EntityManagerFactory emf;
	EntityManager em;
	
//	@Before
//	public void criarEntitys(){
//		System.out.println("Inicializando testes... ");
//		emf = Persistence.createEntityManagerFactory("globosat");
//		em = emf.createEntityManager();
//	}
	
	@Test
	public void testPesquisaDeAssunto() throws SQLException {
		emf = Persistence.createEntityManagerFactory("globosat");
		em = emf.createEntityManager();
		AssuntoDao assuntos = new AssuntoDao(em);
		Assert.assertNotEquals(assuntos.getAssunto(1).getIdAssunto(),0);
		
		em.close();
		emf.close();
	}

//	@Test
//	public void testPesquisaDeAssuntoComIdPerfil() throws SQLException {
//		PerfilDao perfilAssunto = new PerfilDao(em);
//		
//		Assert.assertNotNull(perfilAssunto.getPerfilAssuntoResolvendoLazy(1).getAssuntos());
//		Assert.assertEquals(1,perfilAssunto.getPerfilAssuntoResolvendoLazy(1).getAssuntos().get(0).getIdAssunto());
//	}
	
	@Test
	public void testPesquisaDeNoticiaComIdAssunto() throws SQLException {
		emf = Persistence.createEntityManagerFactory("globosat");
		em = emf.createEntityManager();
		
		AssuntoDao noticiaAssunto = new AssuntoDao(em);
		
		System.out.println("INFOO1"+noticiaAssunto.getNoticiaAssuntoResolvendoLazy(1).get(0).getNoticia());
		System.out.println("INFOO1"+noticiaAssunto.getNoticiaAssuntoResolvendoLazy(1).get(0).getLink());
		Assert.assertEquals(1,noticiaAssunto.getNoticiaAssuntoResolvendoLazy(1).get(0).getIdNoticia());
		
		em.close();
		emf.close();
	}

//	@After
//	public void tearDown(){
//		em.close();
//		emf.close();
//		System.out.println("Tester terminados... ");
//	}
}
