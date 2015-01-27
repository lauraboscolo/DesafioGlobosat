package br.com.dextra.database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.Gson;

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
		
		this.assuntos = new AssuntoDao(em);
		this.perfis   = new PerfilDao(em);
		this.noticias = new NoticiaDao(em);
	}
	
	public String getNoticiasPersonalizadas(int idPerfil){
		// Pegar idPerfil
		// Procurar na tabela de perfis_assuntos
		// Procurar idAssunto encontrado nas noticias
		List<Assunto> assuntosDePerfil;
		assuntosDePerfil = perfis.getPerfilAssuntoResolvendoLazy(idPerfil).getAssuntos();
		
		List<Noticia> noticias = new ArrayList<Noticia>();
		for (Assunto adp : assuntosDePerfil){
			List<Noticia> noticiasDoAssunto = assuntos.getNoticiaAssuntoResolvendoLazy(adp.getIdAssunto());
			
			for (Noticia n : noticiasDoAssunto){
				noticias.add(n);
			}
		}
		
		return gerarJsonDeNoticias(noticias);
	}
	
	public String getTodasAsNoticias(){
		return gerarJsonDeNoticias(noticias.getTodasNoticias());
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
