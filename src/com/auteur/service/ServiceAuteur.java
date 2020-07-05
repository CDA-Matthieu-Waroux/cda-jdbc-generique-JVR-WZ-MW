package com.auteur.service;

import java.util.List;

import com.jdbc.dao.IAuteurDao;
import com.jdbc.dao.bdd.AuteurDaoImpl;
import com.librairie.model.personne.Auteur;

public class ServiceAuteur {
	
	private static IAuteurDao auteurDao = new AuteurDaoImpl();
	
	public static List<Auteur> getAuteurs() {
		
		List<Auteur> auteurs = auteurDao.readAll();
		for (Auteur a : auteurs) {
			System.out.println(a);
		}
		return auteurs;
	}
}
