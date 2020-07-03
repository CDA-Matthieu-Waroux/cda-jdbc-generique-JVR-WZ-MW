package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;


public abstract class Personne {

	protected String nom;
	protected String prenom;
	protected byte age;
	protected int id;
	protected Adresse adresse;
	
	public Personne() {
		
	}
	
	public Personne(String nom, String prenom, byte age, int id, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.id = id;
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
}
