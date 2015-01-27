package br.com.dextra.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import weka.core.Instance;
import weka.core.Instances;
import br.com.dextra.WEKA.PD;



public class PDTest {

	@Test(expected = FileNotFoundException.class) 
	public void testPD() throws FileNotFoundException {
		PD pdT = new PD("arquivoInexistente");
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
			// A precisao utilizando como teste o proprio arquivo de treinamento deve ser 100%
			assertTrue(pdT.getPrecisao("src/main/resources/dados_treinamento.ARFF") == 1.0);
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
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
			// A classificacao deve ser correta, uma vez que, as instancias carregadas do arquivo sao as do treinamento
			assertTrue(instanciaAtual.classValue() == pdT.classificacaoInt(instanciaAtual));
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
		}
	}
	

	

}
