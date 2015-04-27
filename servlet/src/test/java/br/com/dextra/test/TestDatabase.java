package br.com.dextra.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.dextra.database.AcessoDao;


public class TestDatabase {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public void init(){
		emf = Persistence.createEntityManagerFactory("globosat");
		em = emf.createEntityManager();
	}
	
	public void cadastrarAcesso(){
		init();
		AcessoDao acessoDao = new AcessoDao(em);
		acessoDao.addAcesso(1, 1, 0);
		acessoDao.addAcesso(1, 7, 0);
		acessoDao.addAcesso(2, 1, 0);
		acessoDao.addAcesso(2, 3, 0);
		acessoDao.addAcesso(2, 5, 0);
		acessoDao.addAcesso(2, 6, 0);
		acessoDao.addAcesso(2, 7, 0);
		acessoDao.addAcesso(3, 2, 0);
		acessoDao.addAcesso(3, 4, 0);
		acessoDao.addAcesso(3, 7, 0);
		acessoDao.addAcesso(4, 5, 0);
		acessoDao.addAcesso(4, 7, 0);
		acessoDao.addAcesso(4, 8, 0);
	}
	
}
