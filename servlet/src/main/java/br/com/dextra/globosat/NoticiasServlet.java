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
	private String BIG_DATA_PATH;
	
	private static final long serialVersionUID = 2395124688530916076L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caracteristicas = request.getParameter("caracteristicas").toString();
		
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(caracteristicas, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		
		PrintWriter writer = response.getWriter();
		writer.println("paaah");
		writer.flush();
		Instance instancia = createInstance(jsonObject.toString(), writer);
		PD pd = new PD(BIG_DATA_PATH);
		try {
			pd.treinar();
			writer.println("test");
			writer.flush();
			//double indexClassi = pd.classificacaoInt(instancia)+1;
			//writer.println("class: " + indexClassi);
			/////////////////////////////
			//bancodedados(indexClassi);
			////////////////////////////
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println("oiii");
		
		writer.flush();
		writer.close();
	}
	
	private Instance createInstance(String json, PrintWriter out)
	{
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		out.println(usuario.getCarreira()+usuario.getClasse()+usuario.getIdade()+usuario.getLema()+usuario.getParticipacao());
		out.println("1");
		out.flush();
		ServletContext context = getServletContext();
		Instance instancia = new DenseInstance(5); 
		try{
			instancia.setValue(new Attribute("lema"), usuario.getLema());
			instancia.setValue(new Attribute("participacao"), usuario.getParticipacao());
			instancia.setValue(new Attribute("idade"), usuario.getIdade());
			instancia.setValue(new Attribute("carreira"), usuario.getCarreira());
			instancia.setValue(new Attribute("classe"), usuario.getClasse());
		}catch(Exception e)
		{
			context.log(e.getMessage());
		}
		out.println("5");
		out.flush();
		return instancia;
	}

}
