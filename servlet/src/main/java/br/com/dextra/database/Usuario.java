package br.com.dextra.database;

public class Usuario {
	private String lema;
	private double participacao;
	private double idade;
	private String carreira;
	private String classe;
	private String perfil;
	public String getLema() {
		return lema;
	}
	public void setLema(String lema) {
		this.lema = lema;
	}
	public double getParticipacao() {
		return participacao;
	}
	public void setParticipacao(double participacao) {
		this.participacao = participacao;
	}
	public double getIdade() {
		return idade;
	}
	public void setIdade(double idade) {
		this.idade = idade;
	}
	public String getCarreira() {
		return carreira;
	}
	public void setCarreira(String carreira) {
		this.carreira = carreira;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
}
