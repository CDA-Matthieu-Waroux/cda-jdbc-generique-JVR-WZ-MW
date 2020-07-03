package com.utils.input.utilisateur;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jdbc.dao.ILivreDao;
import com.jdbc.dao.bdd.LivreDaoImpl;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Editeur;

public class UtilsLivre {
	private static Scanner sc = new Scanner(System.in);

	public static Livre createLivre(Editeur editeurSelection, Auteur auteurSelection) {
		int ref = askRef();
		int nombrePage = askNombrePage();
		int prix = askPrix();
		int quantitee = askQuantitee();
		String titre = askTitreLivre();
		return new Livre(titre, auteurSelection, nombrePage, editeurSelection, prix, ref, quantitee);
	}

	private static String askTitreLivre() {
		return sc.nextLine();
	}

	private static int askQuantitee() {
		System.out.println("Quel est la quantitée du livre en stock ? :");
		int reponse = 0;
		while(true) {
			try {
				reponse = Integer.parseInt(sc.nextLine());
				return reponse;
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("Entrez un chiffre");
			}
		}
	}

	private static int askPrix() {
		System.out.println("Quel est le prix du livre ? :");
		int reponse = 0;
		while(true) {
			try {
				reponse = Integer.parseInt(sc.nextLine());
				return reponse;
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("Entrez un chiffre");
			}
		}
	}

	private static int askNombrePage() {
		System.out.println("Combien de page contient le livre ? :");
		int reponse = 0;
		while(true) {
			try {
				reponse = Integer.parseInt(sc.nextLine());
				return reponse;
			} catch (InputMismatchException | NumberFormatException e) {
				System.out.println("Entrez un chiffre");
			}
		}
	}

	private static int askRef() {
		ILivreDao livreDao = new LivreDaoImpl();
		List<Livre> livres = livreDao.readAll();
		return livres.size();
	}
}
