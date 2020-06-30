package com.librairie.model.compte;

public class Compte {

	TypeCompte type;
	private String login;
	private String password;
	private int idCompte;

	public Compte() {
	}

	public Compte(TypeCompte type, String login, String password, int idCompte) {
		super();
		this.type = type;
		this.login = login;
		this.password = password;
		this.idCompte = idCompte;
	}

	public TypeCompte getType() {
		return type;
	}

	public void setType(TypeCompte type) {
		this.type = type;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

}
