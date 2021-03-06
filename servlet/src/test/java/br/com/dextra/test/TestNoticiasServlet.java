package br.com.dextra.test;

import java.io.FileNotFoundException;

import org.junit.Test;

import weka.core.Instance;
import br.com.dextra.WEKA.PD;
import br.com.dextra.globosat.NoticiasServlet;
import static org.junit.Assert.*;
public class TestNoticiasServlet {

	@Test
	public void createInstanceTest()
	{
		PD pd;
		try {
			pd = new PD("src/main/resources/dados_treinamento.ARFF");
			pd.treinar();
			NoticiasServlet sv = new NoticiasServlet();
			Instance inst = sv.createInstance("{\"lema\":\"MODA\",\"participacao\":\"0.3\",\"idade\":28,\"carreira\":\"PUBLICIDADE\",\"classe\":\"B\"}");
			
			assertEquals(2,inst.value(0), 0.0); //comparando lema moda = 3.0
			assertEquals(0.3, inst.value(1), 0.0); //comparando participacao
			assertEquals(28, inst.value(2), 0.0); //comparando idade =  28
			assertEquals(9, inst.value(3), 0.0); //comparando carreira PUBLICIDADE = 9.0
			assertEquals(1, inst.value(4), 0.0); //comparando classe B = 1.0
		} catch (FileNotFoundException e) {
			fail("arquivo nao ARFF encontrado");
			e.printStackTrace();
		} catch (Exception e) {
			fail("falha no treinamento");
			e.printStackTrace();
		}
	}
}
