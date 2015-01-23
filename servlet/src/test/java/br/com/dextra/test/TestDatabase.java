package br.com.dextra.test;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextra.database.AssuntoDao;

public class TestDatabase {
	
	@Test
	public void testInsercaoAssunto() throws SQLException {
		
//		String url = "jdbc:postgresql://dbserver:5443/desafio_globosat";
//		Properties props = new Properties();
//		props.setProperty("user", "desafio_globosat");
//		props.setProperty("password", "desafio321");
//		//props.setProperty("ssl", "false");
//		Connection conn = DriverManager.getConnection(url, props);
//		
//		Statement stmt = conn.createStatement();
//		
//		ResultSet query = stmt.executeQuery("select noticia from noticias");
//		
//		while (query.next()) {
//			System.out.println(query.getString(1));
//		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("globosat");
		EntityManager em = emf.createEntityManager();
		
		AssuntoDao assuntos = new AssuntoDao(em);
		System.out.println(assuntos.getAssunto(1).getAssunto());
		
		em.close();
		emf.close();
	}

}
