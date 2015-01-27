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
			
			assertEquals(pd.classificacaoInt(inst), null);
		} catch (FileNotFoundException e) {
			fail("arquivo nao ARFF encontrado");
			e.printStackTrace();
		} catch (Exception e) {
			fail("falha no treinamento");
			e.printStackTrace();
		}
	}
}
