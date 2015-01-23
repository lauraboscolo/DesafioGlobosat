package br.com.dextra.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import weka.core.Instance;
import weka.core.Instances;
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
			PD pdT = new PD("src/main/resources/dados_treinamento.ARFF");
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
			PD pdT = new PD("src/main/resources/dados_treinamento.ARFF");
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
			PD pdT = new PD("src/main/resources/dados_treinamento.ARFF");
			pdT.treinar();
			pdT.getPrecisao("src/main/resources/arfftest.arff");
			assertTrue(pdT.getPrecisao("src/main/resources/dados_treinamento.ARFF") == 1.0);
			assertTrue(true);
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClassificacaoInt()
	{
		try {
			PD pdT = new PD("src/main/resources/dados_treinamento.ARFF");
			pdT.treinar();
			FileReader fl = new FileReader("src/main/resources/dados_treinamento.ARFF");
			
			Instances instancias = new Instances(fl);
			instancias.setClassIndex(instancias.numAttributes()-1);
			Instance instanciaAtual = instancias.get(0);
			assertTrue(instanciaAtual.classValue() == pdT.classificacaoInt(instanciaAtual));
			assertTrue(true);
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
			e.printStackTrace();
		}
	}
	
	

}
