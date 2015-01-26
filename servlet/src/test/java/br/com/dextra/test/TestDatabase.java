package br.com.dextra.test;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextra.database.AssuntoDao;
import br.com.dextra.database.PerfilDao;
import br.com.dextra.database.Noticia;
import br.com.dextra.database.Gerenciadora;

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
	
	@Test
	public void testGerarJsonNoticias() {
		
		Noticia n = new Noticia(1, "Teste 1","teste 1");
		Noticia n2 = new Noticia(1, "Teste 2","teste 2");
		Noticia n3 = new Noticia(1, "Teste 3","teste 3");

		List<Noticia> noticias = new ArrayList<Noticia>();
		noticias.add(n);
		noticias.add(n2);
		noticias.add(n3);
		
		Gerenciadora geren = new Gerenciadora();
		String retorno = geren.gerarJsonDeNoticias(noticias);
		String esperado = ""; 
		
		Assert.assertNotEquals(retorno,esperado);
		
		
		
	}

}
