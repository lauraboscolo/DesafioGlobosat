package br.com.dextra.globosat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

}
