package br.com.dextra.test;

import org.junit.Assert;

import org.junit.Test;

import br.com.dextra.database.AssuntoDao;

public class TestDatabase {
	
	@Test
	public void testInsercaoAssunto(){
		AssuntoDao assuntos = new AssuntoDao();
		Assert.assertTrue(assuntos.addAssunto("Cultura"));
	}

}
