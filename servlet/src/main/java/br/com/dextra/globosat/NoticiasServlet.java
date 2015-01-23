package br.com.dextra.globosat;

import java.awt.font.GlyphJustificationInfo;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import Pojos.Usuario;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class NoticiasServlet extends HttpServlet {
		
	private static final long serialVersionUID = 2395124688530916076L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caracteristicas = request.getParameter("caracteristicas").toString();
		
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(caracteristicas, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		
		PrintWriter writer = response.getWriter();
		writer.print(jsonObject.toString());
		writer.flush();
		writer.close();
	}
	
	private Instance createInstance(String json)
	{
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);
		Instance instancia = new DenseInstance(6); 
		instancia.setValue(new Attribute("lema"), usuario.getLema());
		instancia.setValue(new Attribute("participacao"), usuario.getParticipacao);
		instancia.setValue(new Attribute("idade"), usuario.getIdade());
		instancia.setValue(new Attribute("carreira"), usuario.getCarreira());
		instancia.setValue(new Attribute("classe"), usuario.getClasse());
		instancia.setValue(new Attribute("perfil"), usuario.getPerfil());
		
		return instancia;
	}

}
