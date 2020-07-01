package com.librairie.model.livre;

import com.librairie.model.personne.Auteur;

public class Livre {

	private String titre;
	private Auteur auteur;
	private int nombrePage;
	private String editeur;
	private int prix;
	private int ref;
	private int quantitee;

	public Livre() {
	}

	public Livre(String titre, Auteur auteur, int nombrePage, String editeur, int prix, int ref, int quantitee) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.nombrePage = nombrePage;
		this.editeur = editeur;
		this.prix = prix;
		this.ref = ref;
		this.quantitee = quantitee;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public int getNombrePage() {
		return nombrePage;
	}

	public void setNombrePage(int nombrePage) {
		this.nombrePage = nombrePage;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getQuantitee() {
		return quantitee;
	}

	public void setQuantitee(int quantitee) {
		this.quantitee = quantitee;
	}

	@Override
	public String toString() {
		return "---Titre : " + this.titre + "\n" + 
			   "   Reference : " + this.ref + "\n" + 
			   "   Auteur : " + this.auteur.getPrenom() + " " + this.auteur.getNom() + "\n" +
			   "   Editeur : " + this.editeur + "\n" + 
			   "   Nombre de pages : " + this.nombrePage + "\n" + 
			   "   Prix : " + this.prix + "\n";
	}
}
