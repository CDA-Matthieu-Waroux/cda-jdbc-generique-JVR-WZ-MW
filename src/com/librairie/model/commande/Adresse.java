package com.librairie.model.commande;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder()
public class Adresse {

	private int id;
	private int numero;
	private String rue;
	private String ville;
	private String codePostal;
	private String pays;
}
