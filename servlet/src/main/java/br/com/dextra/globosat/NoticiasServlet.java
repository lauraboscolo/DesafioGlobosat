package br.com.dextra.globosat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import br.com.dextra.WEKA.PD;
import br.com.dextra.database.AcessoDao;
import br.com.dextra.database.Gerenciadora;
import br.com.dextra.database.Usuario;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class NoticiasServlet extends HttpServlet {

	private final String BIG_DATA_PATH = "src/main/resources/dados_treinamento.ARFF";

	private PD pd;
	private EntityManager em;
	private EntityManagerFactory emf;

	private static final long serialVersionUID = 2395124688530916076L;

	public void init() throws ServletException {
		try {
			emf = Persistence.createEntityManagerFactory("globosat");
			em = emf.createEntityManager();
			pd = new PD(BIG_DATA_PATH);
			pd.treinar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		Integer idAsusnto = Integer.parseInt(req.getParameter("caracteristicas"));
		String caracteristicas = req.getParameter("caracteristicas").toString();
		AcessoDao acessoDao = new AcessoDao(em);
		
		try {
			acessoDao.adicionarAcesso(idAsusnto,
					(int) classificarUsuario(caracteristicas));
		} catch (Exception e) {
			writer.println(e.getMessage());
			writer.flush();
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caracteristicas = request.getParameter("caracteristicas").toString();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();

		try {
			double indexClassi = -1;
			indexClassi = classificarUsuario(caracteristicas);
			Gerenciadora ger = new Gerenciadora(em);
			String jsonRetorno = ger.getNoticiasPersonalizadas((int) indexClassi);

			writer.println(jsonRetorno);
			writer.flush();

		} catch (Exception e) {
			writer.println(e.getMessage());
			writer.flush();
			e.printStackTrace();
		}
	}

	private double classificarUsuario(String caracteristicas) throws Exception {
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(caracteristicas, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();

		Instance instancia = createInstance(jsonObject.toString());
		return pd.classificacaoInt(instancia) + 1;
	}

	public Instance createInstance(String json) {
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(json, Usuario.class);

		// //atributos numericos
		Attribute participacao = new Attribute("participacao");
		Attribute idade = new Attribute("idade");

		// nominal
		// LEMA
		ArrayList<String> lemaNomes = new ArrayList<String>();
		lemaNomes.add("EXCLUSIVIDADE");
		lemaNomes.add("INOVACAO");
		lemaNomes.add("MODA");
		lemaNomes.add("SUSTENTABILIDADE");
		Attribute lema = new Attribute("lema", lemaNomes);
		// CARREIRA
		ArrayList<String> carreiraNomes = new ArrayList<String>();
		carreiraNomes.add("PROFESSOR");
		carreiraNomes.add("EMPRESARIO");
		carreiraNomes.add("EXECUTIVO");
		carreiraNomes.add("TECNOLOGIA");
		carreiraNomes.add("FOTOGRAFO");
		carreiraNomes.add("CONSULTORIA");
		carreiraNomes.add("ARQUITETURA");
		carreiraNomes.add("UNIVERSITARIO");
		carreiraNomes.add("ENGENHARIA");
		carreiraNomes.add("PUBLICIDADE");
		carreiraNomes.add("ASSISTENTE");
		Attribute carreira = new Attribute("carreira", carreiraNomes);
		// CLASSE
		ArrayList<String> classeNomes = new ArrayList<String>();
		classeNomes.add("A");
		classeNomes.add("B");
		Attribute classe = new Attribute("classe", classeNomes);
		// PERFIL
		ArrayList<String> perfilNomes = new ArrayList<String>();
		perfilNomes.add("EXPLORADOR");
		perfilNomes.add("VENCEDORES");
		perfilNomes.add("SEGUIDORES");
		perfilNomes.add("TRANSFORMADORES");
		Attribute perfil = new Attribute("perfil", perfilNomes);

		// DATASET
		ArrayList<Attribute> attrs = new ArrayList<Attribute>();
		attrs.add(lema);
		attrs.add(participacao);
		attrs.add(idade);
		attrs.add(carreira);
		attrs.add(classe);
		attrs.add(perfil);

		Instances dataset = new Instances("gerenciadorNoticia", attrs, 0);

		// criando a instancia
		double[] attValues = new double[dataset.numAttributes()];
		attValues[0] = dataset.attribute("lema").indexOfValue(usuario.getLema());
		attValues[1] = usuario.getParticipacao();
		attValues[2] = usuario.getIdade();
		attValues[3] = dataset.attribute("carreira").indexOfValue(
				usuario.getCarreira());
		attValues[4] = dataset.attribute("classe")
				.indexOfValue(usuario.getClasse());
		attValues[5] = dataset.attribute("perfil").indexOfValue("");

		dataset.add(new DenseInstance(1.0, attValues));
		dataset.setClassIndex(dataset.numAttributes() - 1);

		return dataset.firstInstance();

	}

}
