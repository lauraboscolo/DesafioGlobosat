package br.com.dextra.globosat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dextra.database.AcessoDao;
import br.com.dextra.database.Gerenciadora;
import br.com.dextra.database.UsuarioDao;


public class NoticiasServlet extends HttpServlet {

	private final String USUARIO_COOKIE = "IdUsuario";

	private EntityManager em;
	private EntityManagerFactory emf;

	private static final long serialVersionUID = 2395124688530916076L;

	public void init() throws ServletException {
		emf = Persistence.createEntityManagerFactory("globosat");
		em = emf.createEntityManager();
		
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

		//TODO DOJO
		//int idUsuario = getIdUsuarioPorCookie(response, request.getCookies());

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

	private int getIdUsuarioPorCookie(HttpServletResponse response,
			Cookie[] cookies) {
		int idUsuario = -1;

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookieAtual = cookies[i];
			if (cookieAtual.getName().equals(USUARIO_COOKIE)) {
				idUsuario = Integer.parseInt(cookieAtual.getValue());
			}
		}

		if(idUsuario == -1){
			idUsuario = new UsuarioDao(em).getProxUsuario().getQtosUsuarios().intValue();

			Cookie c = new Cookie(USUARIO_COOKIE, Integer.toString(idUsuario));
			response.addCookie(c);
		}
		return idUsuario;
	}

}
