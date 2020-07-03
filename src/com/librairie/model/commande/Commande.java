package com.librairie.model.commande;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

	private int numero_commande;
	private Adresse adresse;
	StatusCommande status;

}
