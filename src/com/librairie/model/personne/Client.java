package com.librairie.model.personne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client extends Utilisateur {

	private int idClient;
	private String nom;
	private String prenom;
	private byte age;
	private int idCompte;

}
