package com.utils.input.utilisateur;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jdbc.dao.IAdresseDao;
import com.jdbc.dao.bdd.AdresseDaoImpl;
import com.librairie.model.commande.Adresse;
import com.librairie.model.personne.Editeur;
import com.librairie.service.editeur.ServiceEditeur;

public class UtilsEditeur {
	
	private static Scanner sc = new Scanner(System.in);

	public static Editeur createEditeur() {
		List<Editeur> editeurs = ServiceEditeur.getEditeurs();
		int idEditeur = (editeurs.get(editeurs.size()-1).getIdEditeur()) + 1;
		System.out.println("Le prochain editeur aura l'id n : " + idEditeur);
		String nomEditieur = askNomEditeur();
		Adresse adresse = UtilsAdresse.askAdresse();
		IAdresseDao adresseDao = new AdresseDaoImpl();
		adresseDao.create(adresse);
		System.out.println("Adresse créée");
		return new Editeur(idEditeur, nomEditieur, adresse);
	}

	public static char askExistanceEditeur() {
		System.out.println("L'éditeur du livre existe-t-il déjà la base de données ? : o/n");
		while(sc.hasNext()) {
			char reponse = sc.next().charAt(0);
		    if(reponse == 'o' || reponse == 'n') {
		    	return reponse;
		    } else {
		    	System.out.println("Entrez 'o' pour oui ou 'n' pour non");
		    }
		}
		return 'n';
	}
	
	public static String askNomEditeur() {
		System.out.println("Quel est le nom de l'editeur : ");
		sc.nextLine();
		String answer = sc.nextLine();
		return answer;
	}
	
	public static Editeur choixEditeur() {
		List<Editeur> editeurs = ServiceEditeur.getEditeurs();
		Editeur editeurSelection = null;
		System.out.println("Choisissez un editeur par son id");
		while(editeurSelection == null) {
			try {
				editeurSelection = editeurs.get(askIdEditeur() - 1);
				
			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Saisissez un nombre entre 1 et " + editeurs.get(editeurs.size()-1).getIdEditeur());
			}
		}
		return editeurSelection;
	}
	
	public static int askIdEditeur() {
		System.out.println("Selectionnez l'id de l'editeur : ");
		int reponse = 0;
		while(true) {
			try {
				reponse = sc.nextInt();
				return reponse;
			} catch (InputMismatchException e) {
				System.out.println("Selectionner un nombre : ");
				sc.next();
			}
		}
	}
}