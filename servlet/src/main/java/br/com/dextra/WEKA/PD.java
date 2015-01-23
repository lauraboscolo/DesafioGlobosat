package br.com.dextra.WEKA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class PD {

	public MultilayerPerceptron treinamento(String file_path) throws Exception {
		FileReader fl = new FileReader(file_path);
		Instances instancia = new Instances(fl);

		MultilayerPerceptron mlp = new MultilayerPerceptron();
		mlp.setAutoBuild(true);
		mlp.setLearningRate(0.3);
		mlp.setMomentum(0.2);
		mlp.setTrainingTime(1000);
		mlp.setHiddenLayers("3");

		// treinando a rede
		mlp.buildClassifier(instancia);

		return mlp;
	}

	public int precisao(MultilayerPerceptron mlp) {
		return 0;
	}
}
