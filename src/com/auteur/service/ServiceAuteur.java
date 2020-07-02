package com.auteur.service;

import java.util.List;

import com.jdbc.dao.IAuteurDao;
import com.jdbc.dao.bdd.AuteurDaoImpl;
import com.librairie.model.personne.Auteur;

public class ServiceAuteur {
	
	public static List<Auteur> getAuteurs() {
		IAuteurDao auteurDao = new AuteurDaoImpl();
		List<Auteur> auteurs = auteurDao.readAll();
		for (Auteur a : auteurs) {
			System.out.println(a);
		}
		return auteurs;
	}
}
