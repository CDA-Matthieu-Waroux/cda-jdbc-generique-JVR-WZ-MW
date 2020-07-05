package com.librairie.model.commande;

import java.math.BigDecimal;

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
	private String date_commande;
	private BigDecimal prix_total;
	private String nom;
	private String prenom;
	private String libele_status_commande;

	@Override
	public String toString() {
		return "-------------------\nNumero commande : " + numero_commande + "\nDate : " + date_commande
				+ "\nPrix de commande : " + prix_total + "\nNom : " + nom + "\nPrenom : " + prenom
				+ "\nStatus commande : " + libele_status_commande;
	}

}
