package br.com.dextra.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.gson.Gson;

public class Gerenciadora {
	
	private AssuntoDao assuntoDao;
	private PerfilDao perfilDao;
	
	private EntityManager em;
	private EntityManagerFactory emf;

	public Gerenciadora(EntityManager em) {
		this.assuntoDao = new AssuntoDao(em);
		this.perfilDao   = new PerfilDao(em);
	}
	
	/**
	 * Descobre a lista de notícias desejadas para o id do perfil informado
	 * no parâmetro
	 * @param idPerfil (id do perfil do usuário)
	 * @return Array de JSon com notícias, em forma de String
	 */
	public String getNoticiasPersonalizadas(int idPerfil){
		List<Acesso> assuntosDePerfil;
		assuntosDePerfil = perfilDao.getPerfilAssuntoResolvendoLazy(idPerfil).getPerfilAssunto();
		System.out.println("Tamando : " + assuntosDePerfil.size());
		List<Noticia> noticias = new ArrayList<Noticia>();
		for (Acesso perfilAssunto : assuntosDePerfil){
			System.out.println("Acesso :" + perfilAssunto.getAssunto());
			List<Noticia> noticiasDoAssunto = assuntoDao.getNoticiaAssuntoResolvendoLazy(perfilAssunto.getAssunto());
			for (Noticia n : noticiasDoAssunto ){
				noticias.add(n);
			}
		}
		
		return gerarJsonDeNoticias(noticias);
	}

	/**
	 * Método para gerar uma String json passando um array de noticias
	 * @param array que sera gerado o json
	 * 
	 */
	public String gerarJsonDeNoticias(List<Noticia>noticias){
		Gson gson = new Gson();
		
		String result = "["; // inicio da string que representa um array no json
		for (int i = 0; i < noticias.size(); i++) {
			if (i != noticias.size()-1)
				result = result + gson.toJson(noticias.get(i)) + ",";
			else 
				result = result + gson.toJson(noticias.get(i));
		}
		return result + "]";
				
	}
	
	public void close(){
		em.close();
		emf.close();
	}
}
