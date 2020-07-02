package com.librairie.model.personne;

import com.librairie.model.commande.Adresse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Editeur {
	
	private int idEditeur;
	private String nom;
	private Adresse adresse;
}
