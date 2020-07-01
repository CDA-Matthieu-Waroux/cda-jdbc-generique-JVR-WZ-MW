package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Utilisateur extends Personne {

	protected String login;
	protected String password;
	protected int gradeUtilisateur;

	public Utilisateur(String nom, String prenom, byte age, int id, Adresse adresse, String login, String password,
			int gradeUtilisateur) {
		super(nom, prenom, age, id, adresse);
		this.login = login;
		this.password = password;
		this.gradeUtilisateur = gradeUtilisateur;
	}

}
