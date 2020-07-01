package com.librairie.model.commande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {

	private int numero;
	private String rue;
	private String ville;
	private String codePostal;
	private String pays;

}
