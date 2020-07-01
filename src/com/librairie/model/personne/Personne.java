package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class Personne {

	protected String nom;
	protected String prenom;
	protected byte age;
	protected int id;
	protected Adresse adresse;

}
