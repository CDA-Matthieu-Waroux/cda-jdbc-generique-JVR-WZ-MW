package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;

public class Auteur extends Personne {

	public Auteur() {
		
	}
	
	public Auteur(String nom, String prenom, byte age, int id, Adresse adresse) {
		super(nom, prenom, age, id, adresse);
	}
	
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", age=" + age + "]";
	}
}
