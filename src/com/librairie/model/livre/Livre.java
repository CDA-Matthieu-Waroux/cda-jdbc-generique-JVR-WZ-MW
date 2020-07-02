package com.librairie.model.livre;

import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Editeur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Livre {

	private int ref;
	private int nombrePage;
	private int prix;
	private int quantitee;
	private String titre;
	private Auteur auteur;
	private Editeur editeur;


	@Override
	public String toString() {
		return "---Titre : " + this.titre + "\n" + 
			   "   Reference : " + this.ref + "\n" + 
			   "   Auteur : " + this.auteur.getPrenom() + " " + this.auteur.getNom() + "\n" +
			   "   Editeur : " + this.editeur.getNom() + "\n" + 
			   "   Nombre de pages : " + this.nombrePage + "\n" + 
			   "   Prix : " + this.prix + "\n";
	}
}
