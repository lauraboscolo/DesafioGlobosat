package br.com.dextra.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.gson.Gson;

public class Gerenciadora {

	private NoticiaDao noticiaDao;

	private EntityManager em;
	private EntityManagerFactory emf;

	public Gerenciadora(EntityManager em) {
		this.noticiaDao = new NoticiaDao(em);
	}

	public String getNoticiasPersonalizadas() {
		return gerarJsonDeNoticias(noticiaDao.getAlgumasNoticas(10));
	}

	public String gerarJsonDeNoticias(List<Noticia> noticias) {
		Gson gson = new Gson();

		String result = "[";
		for (int i = 0; i < noticias.size(); i++) {
			if (i != noticias.size() - 1)
				result = result + gson.toJson(noticias.get(i)) + ",";
			else
				result = result + gson.toJson(noticias.get(i));
		}
		return result + "]";
	}

	public void close() {
		em.close();
		emf.close();
	}
}
