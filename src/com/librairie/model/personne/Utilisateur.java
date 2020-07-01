package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;
import com.librairie.model.compte.Compte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor

public class Utilisateur extends Personne {

	protected Compte cp;

	public Utilisateur(String nom, String prenom, byte age, int id, Adresse adresse, String login, String password,
			int gradeUtilisateur) {
		super(nom, prenom, age, id, adresse);

	}

}
