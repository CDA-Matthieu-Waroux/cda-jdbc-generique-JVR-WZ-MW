package com.utils.input.utilisateur;

import java.util.List;
import java.util.Scanner;

import com.jdbc.dao.ILivreDao;
import com.jdbc.dao.bdd.LivreDaoImpl;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Editeur;
import com.librairie.service.livre.ServiceLivre;
import com.librairie.utils.Utils;

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
	
	public static void updateLivre() {
		Livre livreToUpdate = askRefUpdateLivre();
		System.out.println("livre selectionné = " + livreToUpdate);
		ILivreDao livreDao = new LivreDaoImpl();
		int nombrePage = askNombrePage();
		int prix = askPrix();
		int quantitee = askQuantitee();
		String titre = askTitreLivre();
		Auteur auteur = null;
		Editeur editeur = null;
		char changerAuteur = UtilsAuteur.askChangeAuteur();
		char changerEditeur = UtilsEditeur.askChangeEditeur();
		
		if(changerAuteur == 'Y') {
			auteur = UtilsAuteur.createAuteur();
		} else if (changerAuteur == 'N') {
			auteur = livreToUpdate.getAuteur();
		}
		if(changerEditeur == 'Y') {
			editeur = UtilsEditeur.createEditeur();
		} else if (changerEditeur == 'N') {
			editeur = livreToUpdate.getEditeur();
		}

		Livre livreUpdate = new Livre(nombrePage, prix, livreToUpdate.getRef(), quantitee, titre, auteur, editeur);
		livreDao.update(livreUpdate);
		System.out.println("Livre update");
	}

	private static String askTitreLivre() {
		System.out.println("Quel est le titre du livre ? : ");
		return sc.nextLine();
	}

	private static int askQuantitee() {
		System.out.println("Quel est la quantitée du livre en stock ? :");
		return Utils.readInt();
	}

	private static int askPrix() {
		System.out.println("Quel est le prix du livre ? :");
		return Utils.readInt();
	}

	private static int askNombrePage() {
		System.out.println("Combien de page contient le livre ? :");
		return Utils.readInt();
	}

	private static int askRef() {
		ILivreDao livreDao = new LivreDaoImpl();
		List<Livre> livres = livreDao.readAll();
		return livres.size() + 1;
	}
	
	private static Livre askRefUpdateLivre() {
		ILivreDao livreDao = new LivreDaoImpl();
		List<Livre> livres = livreDao.readAll();
		ServiceLivre.readLine();
		System.out.println("Quel référence souhaitez-vous éditer ? :");
		int ref = Utils.readInt();
		while(ref < 0 || ref > livres.size()) {
			System.out.println("Index non trouvée");
			System.out.println("Saisir à nouveau : ");
			ref = Utils.readInt();
		}
		return livres.get(ref-1);
	}
}
