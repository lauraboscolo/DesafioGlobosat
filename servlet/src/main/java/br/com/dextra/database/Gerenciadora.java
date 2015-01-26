package br.com.dextra.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.*;

public class Gerenciadora {
	
	/**
	 * Classes de manipulação direta no banco
	 */
	private NoticiaDao noticias;
	private AssuntoDao assuntos;
	private PerfilDao perfis;
	
	private EntityManager em;
	private EntityManagerFactory emf;
	/*
	 * Classe gerenciadora de tabelas
	 */
	public Gerenciadora() {
		// Para fazer acesso ao banco
		emf = Persistence.createEntityManagerFactory("globosat");
		em = emf.createEntityManager();
		
		this.noticias = new NoticiaDao(em);
		this.assuntos = new AssuntoDao(em);
		this.perfis   = new PerfilDao(em);

	}
	
	public JsonObject getNoticiasPersonalizadas(int idPerfil){
		// Pegar idPerfil
		// Procurar na tabela de perfis_assuntos
		// Procurar idAssunto encontrado nas noticias
		List<Assunto> assuntosDePerfil;
		assuntosDePerfil = perfis.getPerfilAssuntoResolvendoLazy(idPerfil).getAssuntos();
		
		return null;
	}
	
	/**
	 * Método para gerar uma String json passando um array de noticias
	 * @param noticias: array que sera gerado o json
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
