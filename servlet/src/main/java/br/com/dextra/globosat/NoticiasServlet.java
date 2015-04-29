package br.com.dextra.globosat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import br.com.dextra.database.Acesso;
import br.com.dextra.database.AcessoDao;
import br.com.dextra.database.Gerenciadora;
import br.com.dextra.database.UsuarioDao;

public class NoticiasServlet extends HttpServlet {

	private final String USUARIO_COOKIE = "IdUsuario";

	private EntityManager em;
	private EntityManagerFactory emf;
	private UserBasedRecommender recommender;

	private static final long serialVersionUID = 2395124688530916076L;

	public static void main(String args[]) throws ServletException {
		NoticiasServlet ns = new NoticiasServlet();
	}

	public NoticiasServlet() throws ServletException {
		init();
		metodo(10);
	}

	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("globosat");
		em = emf.createEntityManager();
		create();
	}

	private void create() {
		try {
			AcessoDao acessoDao = new AcessoDao(em);

			Map<Integer, List<Acesso>> acessos = acessoDao.getTodosAcessos();
			PrintWriter writer = new PrintWriter("/tmp/datac.txt", "UTF-8");
			for (Integer userId : acessos.keySet()) {
				List<Acesso> acessosUsuario = acessos.get(userId);
				int maior = acessosUsuario.get(acessosUsuario.size() - 1)
						.getQtdAcesso().intValue();
				for (Acesso acessoUsuario : acessosUsuario) {
					double nota = calcularNota(acessoUsuario.getQtdAcesso()
							.intValue(), maior);
					writer.println(String.format(Locale.ENGLISH, "%d,%d,%.1f",
							userId, acessoUsuario.getAssunto(), nota));
				}
			}
			writer.close();

			buildRecomender();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildRecomender() throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/tmp/data.txt"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
//     	 UserNeighborhood neighborhood =
//      		   	 new NearestNUserNeighborhood(3,
//      		   	 similarity, model);
		recommender = new GenericUserBasedRecommender(model, neighborhood,
				similarity);
	}

	private double calcularNota(int qtdAcesso, int maior) {
		return 5d * qtdAcesso / maior;
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		Integer idAssunto = Integer.parseInt(req.getParameter("idAssunto"));
		int idUsuario = getIdUsuarioPorCookie(resp, req.getCookies());

		AcessoDao acessoDao = new AcessoDao(em);

		try {
			acessoDao.adicionarAcesso(idAssunto, idUsuario);
		} catch (Exception e) {
			writer.println(e.getMessage());
			writer.flush();
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();

		int idUsuario = getIdUsuarioPorCookie(response, request.getCookies());
		metodo(idUsuario);
		try {
			Gerenciadora ger = new Gerenciadora(em);
			String jsonRetorno = ger.getNoticiasPersonalizadas();

			writer.println(jsonRetorno);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
			writer.println(e.getMessage());
			writer.flush();
		}
	}

	private void metodo(int idUsuario) {
		try {
			List<RecommendedItem> list = recommender.recommend(idUsuario, 5);
			System.out.println(list.size());
			for (RecommendedItem r : list) {
				System.out.println(r);
			}
		} catch (TasteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private int getIdUsuarioPorCookie(HttpServletResponse response,
			Cookie[] cookies) {
		int idUsuario = -1;

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookieAtual = cookies[i];
			if (cookieAtual.getName().equals(USUARIO_COOKIE)) {
				idUsuario = Integer.parseInt(cookieAtual.getValue());
			}
		}

		if (idUsuario == -1) {
			idUsuario = new UsuarioDao(em).getProxUsuario().getQtosUsuarios()
					.intValue();

			Cookie c = new Cookie(USUARIO_COOKIE, Integer.toString(idUsuario));
			response.addCookie(c);
		}
		return idUsuario;
	}

}
