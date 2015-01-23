package br.com.dextra.WEKA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
public class PD {
	//CONSTANTES
	private boolean AUTO_BUILD = true;
	private double LEARN_RATE = 0.3;
	private String HIDDEN_LAYERS = "5"; 
	private int TRAINING_TIME = 1000; 
	private double MOMENTUM = 0.2; 
	
	//Var Globais
	private String _big_data_path;
	private MultilayerPerceptron _mlp;
	
	public PD(String big_data_path) throws FileNotFoundException
	{
		this._big_data_path = big_data_path;
		//teste de existencia de arquivo
		FileReader fl = new FileReader(_big_data_path);
	}
	
	
	/**
	 * Método que permite que a rede seja treinada de forma padrao utilizando do big_data
	 * atual.
	 * @throws Exception falha na construcao do classificador
	 */
	public void treinar() throws Exception 
	{
		FileReader fl = new FileReader(_big_data_path);
		Instances instancia = new Instances(fl);
		
		_mlp = new MultilayerPerceptron();
		_mlp.setAutoBuild(AUTO_BUILD);
		_mlp.setLearningRate(LEARN_RATE);
		_mlp.setMomentum(MOMENTUM);
		_mlp.setTrainingTime(TRAINING_TIME);
		_mlp.setHiddenLayers(HIDDEN_LAYERS);
		
		//treinando a rede
		_mlp.buildClassifier(instancia);
	}
	/**
	 * Método que permite que a rede seja treinada de forma especificada utilizando do big_data
	 * atual.
	 * @throws Exception
	 */
	public void treinar(int training_time, double learn_rate, double momentum, String hidden_layers) throws Exception
	{
		FileReader fl = new FileReader(_big_data_path);
		Instances instancia = new Instances(fl);
		
		_mlp = new MultilayerPerceptron();
		_mlp.setAutoBuild(AUTO_BUILD);
		_mlp.setLearningRate(learn_rate);
		_mlp.setMomentum(momentum);
		_mlp.setTrainingTime(training_time);
		_mlp.setHiddenLayers(hidden_layers);
		
		//treinando a rede
		_mlp.buildClassifier(instancia);
	}
	
}
