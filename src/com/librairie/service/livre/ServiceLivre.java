package com.librairie.service.livre;

import java.util.List;
import java.util.Scanner;

import com.jdbc.dao.IAuteurDao;
import com.jdbc.dao.IEditeurDao;
import com.jdbc.dao.ILivreDao;
import com.jdbc.dao.bdd.AuteurDaoImpl;
import com.jdbc.dao.bdd.EditeurDaoImpl;
import com.jdbc.dao.bdd.LivreDaoImpl;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Editeur;
import com.utils.input.utilisateur.UtilsAuteur;
import com.utils.input.utilisateur.UtilsEditeur;
import com.utils.input.utilisateur.UtilsLivre;

public class ServiceLivre {

	public static Scanner sc = new Scanner(System.in);

	public static void readLine() {
		ILivreDao livreDao = new LivreDaoImpl();
		List<Livre> livres = livreDao.readAll();
		for (Livre livre : livres) {
			System.out.println(livre);
		}
	}

	public static void createLivre() {
		// selection de l'editeur
		char editeurExiste = UtilsEditeur.askExistanceEditeur();
		Editeur editeurSelection = null;

		if (editeurExiste == 'Y') {
			editeurSelection = UtilsEditeur.choixEditeur();
		} else if (editeurExiste == 'N') {
			editeurSelection = UtilsEditeur.createEditeur();
			IEditeurDao editeurDao = new EditeurDaoImpl();
			editeurDao.create(editeurSelection);
		}
		System.out.println("Editeur : " + editeurSelection);
		
		// selection de l'auteur
		char auteurExiste = UtilsAuteur.askExistanceAuteur();
		Auteur auteurSelection = null;
		if (auteurExiste == 'Y') {
			auteurSelection = UtilsAuteur.choixAuteur();
		} else if (auteurExiste == 'N') {
			auteurSelection = UtilsAuteur.createAuteur();
			IAuteurDao auteurDao = new AuteurDaoImpl();
			auteurDao.create(auteurSelection);
		}
		System.out.println("Auteur : " + auteurSelection);
		
		// creation du livre
		Livre livre = UtilsLivre.createLivre(editeurSelection, auteurSelection);
		ILivreDao livreDao = new LivreDaoImpl();
		livreDao.create(livre);
	}
	
	public static void updateLivre() {
		UtilsLivre.updateLivre();
	}
	
	public static void supprimerLivre() {
		UtilsLivre.deleteLivre();
	}

	public static void updateQuantiteLivre() {
		UtilsLivre.UpdateQuantiteLivre();
	}
}
