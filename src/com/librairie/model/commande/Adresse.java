package com.librairie.model.commande;

public class Adresse {

	private int numero;
	private String rue;
	private String ville;
	private String codePostal;
	private String pays;

	public Adresse() {
	}

	public Adresse(int numero, String rue, String ville, String codePostal, String pays) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.pays = pays;
	}

}
