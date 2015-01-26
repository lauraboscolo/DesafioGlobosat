package br.com.dextra.test;

import weka.core.Instance;
import br.com.dextra.WEKA.PD;
import br.com.dextra.globosat.NoticiasServlet;

public class TestNoticiasServlet {

	@Test
	public void createInstanceTest()
	{
		PD pd = new PD("src/main/resources/dados_treinamento.ARFF");
		pd.treinar();
		NoticiasServlet sv = new NoticiasServlet();
		Instance inst = sv.createInstance("{lema: 'EXCLUSIVIDADE', participacao: '0.12', idade: 42, carreira: 'EMPRESARIO', classe: 'A'}");
		assertTrue(inst.classValue() == pd.classificacaoInt(inst));
	}
}
