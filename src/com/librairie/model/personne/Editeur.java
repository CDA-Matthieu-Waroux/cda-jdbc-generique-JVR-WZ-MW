package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;

public class Editeur {
	
	private int idEditeur;
	private String nom;
	private Adresse adresse;
	
	public Editeur() {
		
	}
	
	public Editeur(int idEditeur, String nom, Adresse adresse) {
		super();
		this.idEditeur = idEditeur;
		this.nom = nom;
		this.adresse = adresse;
	}

	public int getIdEditeur() {
		return idEditeur;
	}

	public void setIdEditeur(int idEditeur) {
		this.idEditeur = idEditeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Editeur [idEditeur=" + idEditeur + ", nom=" + nom + ", adresse=" + adresse + "]";
	}
}
