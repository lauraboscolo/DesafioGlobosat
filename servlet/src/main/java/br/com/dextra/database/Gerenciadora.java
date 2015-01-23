package br.com.dextra.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.gson.JsonObject;

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
	
	public void close(){
		em.close();
		emf.close();
	}
}
