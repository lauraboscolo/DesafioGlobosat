package br.com.dextra.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import br.com.dextra.WEKA.PD;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;


public class PDTest {

	@Test(expected = FileNotFoundException.class) 
	public void testPD() throws FileNotFoundException {
		PD pdT = new PD("qwertyuiop");
	}

	@Test
	public void testTreinar() {
		try {
			PD pdT = new PD("dados_treinamento.ARFF");
			pdT.treinar();
			assertTrue(true);
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
		}
		
	}

	@Test
	public void testTreinarIntDoubleDoubleString() {
		try {
			PD pdT = new PD("dados_treinamento.ARFF");
			pdT.treinar(1000, 0.3, 0.2, "5");
			assertTrue(true);
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
		}
	}
	
	@Test
	public void testGetPrecisao()
	{
		try {
			PD pdT = new PD("dados_treinamento.ARFF");
			pdT.getPrecisao("arfftest.ARFF");
			assertTrue(true);
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
		}
	}
	
	@Test
	public void testcreateInstancia()
	{
		fail("nao implementei");
	}

}
