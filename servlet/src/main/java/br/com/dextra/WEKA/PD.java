package br.com.dextra.WEKA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;

public class PD {

	private String _big_data_path;
	private MultilayerPerceptron _mlp;
	private int _classf = -1;
	
	public PD(String big_data_path) throws FileNotFoundException
	{
		if (arquivoExiste(big_data_path)){
			this._big_data_path = big_data_path;
		} else {
			throw new FileNotFoundException("Arquivo não encontrado");
		}
	}
	
	public PD(String big_data_path, int classifier) throws FileNotFoundException
	{
		if (arquivoExiste(big_data_path)){
			this._big_data_path = big_data_path;
			this._classf = classifier;
		} else {
			throw new FileNotFoundException("Arquivo não encontrado");
		}
	}
	
	/**
	 * Método que permite que a rede seja treinada de forma padrao utilizando do big_data
	 * atual.
	 * @throws Exception falha na construcao do classificador
	 */
	public void treinar() throws Exception 
	{
		Instances instancias = createInstancia(_big_data_path);
		_mlp = new MultilayerPerceptron();
		_mlp.setAutoBuild(true);		
		//treinando a rede
		_mlp.buildClassifier(instancias);
	}
	/**
	 * Método que permite que a rede seja treinada de forma especificada utilizando do big_data
	 * atual.
	 * @throws Exception
	 */
	public void treinar(int training_time, double learn_rate, double momentum, String hidden_layers) throws Exception
	{
		Instances instancias = createInstancia(_big_data_path);
		
		_mlp = new MultilayerPerceptron();
		_mlp.setLearningRate(learn_rate);
		_mlp.setMomentum(momentum);
		_mlp.setTrainingTime(training_time);
		_mlp.setHiddenLayers(hidden_layers);
		
		//treinando a rede
		_mlp.buildClassifier(instancias);
	}
	
	/**
	 * 
	 * @param instancia instancia que sera classificada pela rede
	 * @return qual a classificacao que a rede atribui a instancia
	 * @throws Exception caso exista erro na instancia
	 */
	public double classificacaoInt(Instance instancia) throws Exception
	{
		return _mlp.classifyInstance(instancia);
	}
	
	
	/**
	 * Método que utiliza de um arquivo ARFF de teste para medir a precisao atual da rede
	 * @param test_file_path caminho do arquivo de teste ARFF
	 * @param class_index index do classificador dos dados do arquivo ARFF 
	 * @return a precisao atual da rede
	 * @throws IOException, Exception Caso nao seja possivel ler o arquivo teste ou exista um erro
	 * de sintaxe do ARFF
	 */
	public double getPrecisao(String test_file_path) throws IOException, Exception
	{
		double precisao = 0;
		
		Instances instancias = createInstancia(test_file_path);
		
		for (int i = 0; i < instancias.numInstances(); ++i) 
		{
			Instance instanciaAtual = instancias.get(i);
			double classValue = instanciaAtual.classValue();
			double classMLP = _mlp.classifyInstance(instanciaAtual);
			if(classMLP == classValue)
			{
				++precisao;
			}
		}
		return precisao/instancias.numInstances();	
	}
	
	private Instances createInstancia(String path)
	{
		try {
			FileReader fl = new FileReader(path);
			Instances instancias = new Instances(fl);
			//caso o classificador seja -1 está setado como padrao para o ultimo atributo
			if(_classf == -1)
			{
				instancias.setClassIndex(instancias.numAttributes()-1);
			} else 
			{
				instancias.setClassIndex(_classf);
			}
			return instancias;
		} catch (IOException e) {
			System.err.println("Erro na leitura do arquivo");
			e.printStackTrace();
		}
		return null;

	}

	private boolean arquivoExiste(String caminhoArquivo) {
		File f = new File(caminhoArquivo);
		return (f.exists() && !f.isDirectory());
	}
}
