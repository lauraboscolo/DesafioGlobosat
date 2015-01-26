package br.com.dextra.globosat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import Pojos.Usuario;
import br.com.dextra.WEKA.PD;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class NoticiasServlet extends HttpServlet {
		
	//CONSTANTES
	private String BIG_DATA_PATH = "src/main/resources/dados_treinamento.ARFF";
	private String BIG_DATA_TEST_PATH = "src/main/resources/arfftest.arff";
	
	private static final long serialVersionUID = 2395124688530916076L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caracteristicas = request.getParameter("caracteristicas").toString();
		PrintWriter writer = response.getWriter();

		Gson gson = new Gson();
		JsonElement element = gson.fromJson(caracteristicas, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		
		Instance instancia = createInstance(jsonObject.toString());
		PD pd = new PD(BIG_DATA_PATH);
		try {
			pd.treinar();
			double indexClassi = pd.classificacaoInt(instancia)+1;
			writer.println("class: " + indexClassi);
			writer.flush();
			/////////////////////////////
			//bancodedados(indexClassi);
			////////////////////////////
		} catch (Exception e) {
			writer.println(e.getMessage());
			writer.flush();
			e.printStackTrace();
		}
		writer.close();
	}
	
	public Instance createInstance(String json)
	{
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		//1.ATTRIBUTES 
		////atributos numericos
		Attribute participacao = new Attribute("participacao");
		Attribute idade = new Attribute("idade");
		   
		
		//nominal
		//LEMA
		ArrayList<String> lemaNomes = new FastVector(); 
		lemaNomes.add("EXCLUSIVIDADE"); 
		lemaNomes.add("INOVACAO"); 
		lemaNomes.add("MODA"); 
		lemaNomes.add("SUSTENTABILIDADE"); 
		Attribute lema = new Attribute("lema", lemaNomes); 
		//CARREIRA
		ArrayList<String> carreiraNomes = new FastVector(); 
		carreiraNomes.add("PROFESSOR"); 
		carreiraNomes.add("EMPRESARIO"); 
		carreiraNomes.add("TECNOLOGIA"); 
		carreiraNomes.add("FOTOGRAFO");  
		carreiraNomes.add("CONSULTORIA");  
		carreiraNomes.add("ARQUITETURA");  
		carreiraNomes.add("UNIVERSITARIO");  
		carreiraNomes.add("ENGENHARIA");  
		carreiraNomes.add("PUBLICIDADE");  
		carreiraNomes.add("ASSISTENTE");  
		Attribute carreira = new Attribute("carreira", carreiraNomes); 
		//CLASSE
		ArrayList<String> classeNomes = new FastVector(); 
		classeNomes.add("A"); 
		classeNomes.add("B"); 
		Attribute classe = new Attribute("classe", classeNomes); 
		//perfil
		ArrayList<String> perfilNomes = new FastVector(); 
		perfilNomes.add("EXPLORADOR"); 
		perfilNomes.add("SEGUIDORES"); 
		perfilNomes.add("TRANSFORMADORES"); 
		perfilNomes.add("VENCEDORES"); 
		Attribute perfil = new Attribute("perfil", perfilNomes); 
		
		//DATASET
		ArrayList<Attribute> attrs = new FastVector();
		attrs.add(lema);
		attrs.add(participacao);
		attrs.add(idade);
		attrs.add(carreira);
		attrs.add(classe);
		attrs.add(perfil);
		
		Instances dataset = new	Instances("gerenciadorNoticia", attrs, 0);
		
		
		//criando a instancia
		//first
		double[] attValues = new double[dataset.numAttributes()]; 
		attValues[0] = dataset.attribute("lema").indexOfValue(usuario.getLema()); 
		attValues[1] = usuario.getParticipacao(); 
		attValues[2] = usuario.getIdade(); 
		attValues[3] = dataset.attribute("carreira").indexOfValue(usuario.getCarreira());
		attValues[0] = dataset.attribute("classe").indexOfValue(usuario.getClasse());
		attValues[0] = dataset.attribute("perfil").indexOfValue("");
		dataset.add(new DenseInstance	(1.0, attValues)); 
		dataset.setClassIndex(dataset.numAttributes()-1);
		
		return dataset.firstInstance();
		
	}

}
