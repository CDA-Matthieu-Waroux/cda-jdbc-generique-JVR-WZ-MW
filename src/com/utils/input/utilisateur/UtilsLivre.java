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
	private static ILivreDao livreDao = new LivreDaoImpl();

	public static Livre createLivre(Editeur editeurSelection, Auteur auteurSelection) {
		int ref = askRef();
		int nombrePage = askNombrePage();
		int prix = askPrix();
		int quantitee = askQuantitee();
		String titre = askTitreLivre();
		return new Livre(titre, auteurSelection, nombrePage, editeurSelection, prix, ref, quantitee);
	}
	
	public static void updateLivre() {
		Livre livreToUpdate = selectkRefLivre();
		System.out.println("livre selectionné = " + livreToUpdate);
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
	
	public static void deleteLivre() {
		Livre livreToDelete = selectkRefLivre();
		System.out.println("livre selectionné = " + livreToDelete);
		livreDao.delete(livreToDelete);
		System.out.println("Livre supprimé");
	}

	private static String askTitreLivre() {
		System.out.println("Quel est le titre du livre ? : ");
		return sc.nextLine();
	}

	private static int askQuantitee() {
		System.out.println("Quel est la quantitée du livre en stock ? :");
		int reponse = Utils.readInt();
		while(reponse < 1) {
			System.out.println("Un chiffre négatif n'est pas valable");
			System.out.println("Entrer la quantitée : ");
			reponse = Utils.readInt();
		}
		return reponse;
	}

	private static int askPrix() {
		System.out.println("Quel est le prix du livre ? :");
		int reponse = Utils.readInt();
		while(reponse < 0) {
			System.out.println("Prix negatif non valable");
			System.out.println("Quel est le prix : ");
			sc.nextLine();
			reponse = Utils.readInt();
		}
		return reponse;
	}

	private static int askNombrePage() {
		System.out.println("Combien de page contient le livre ? :");
		int reponse = Utils.readInt();
		while(reponse < 1) {
			System.out.println("Entrer un nombre de page suppérieur à 1");
			System.out.println("Quel est le nombre de page? : ");
			reponse = Utils.readInt();
		}
		return reponse;
	}

	private static int askRef() {
		List<Livre> livres = livreDao.readAll();
		return livres.size() + 1;
	}
	
	private static Livre selectkRefLivre() {
		List<Livre> livres = livreDao.readAll();
		ServiceLivre.readLine();
		System.out.println("Quel référence souhaitez-vous selectionner ? :");
		int ref = Utils.readInt();
		while(ref < 0 || ref > livres.size()) {
			System.out.println("Index non trouvée");
			System.out.println("Saisir à nouveau : ");
			ref = Utils.readInt();
		}
		return livres.get(ref-1);
	}

	public static void UpdateQuantiteLivre() {
		Livre livreToUpdate = selectkRefLivre();
		System.out.println("livre selectionné = " + livreToUpdate);
		int quantitee = askQuantitee();
		Livre livreUpdate = new Livre(livreToUpdate.getTitre(), livreToUpdate.getAuteur(), livreToUpdate.getNombrePage(),
				livreToUpdate.getEditeur(), livreToUpdate.getPrix(), livreToUpdate.getReference(), quantitee);
		livreDao.updateQuantitee(livreUpdate);
		System.out.println("Quantitée mis à jour");
	}
}
