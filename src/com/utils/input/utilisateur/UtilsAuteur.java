package com.utils.input.utilisateur;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.auteur.service.ServiceAuteur;
import com.librairie.model.personne.Auteur;
import com.librairie.utils.Utils;


public class UtilsAuteur {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static Auteur createAuteur() {
		List<Auteur> auteurs = ServiceAuteur.getAuteurs();
		sc.nextLine();
		String prenom = askPrenomAuteur();
		String nom = askNomAuteur();
		byte age = askAgeAuteur();
		int id = (auteurs.get(auteurs.size() - 1).getId()) + 1;
		Auteur auteur = new Auteur(nom, prenom, age, id, null);
		return auteur;
	}

	public static byte askAgeAuteur() {
		System.out.println("Entrer l'age de l'auteur : ");
		return Utils.readByte();
	}

	public static String askNomAuteur() {
		System.out.println("Entrer le nom de l'auteur : ");
		return sc.nextLine();
	}

	public static String askPrenomAuteur() {
		System.out.println("Entrer le prenom de l'auteur : ");
		return sc.nextLine();
	}

	public static Auteur choixAuteur() {
		List<Auteur> auteurs = ServiceAuteur.getAuteurs();
		Auteur auteurSelection = null;
		System.out.println("Choisissez un auteur par son id");
		while(auteurSelection == null) {
			try {
				auteurSelection = auteurs.get(askIdAuteur() - 1);
				
			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Saisissez un nombre entre 1 et " + auteurs.get(auteurs.size()-1).getId());
			}
		}
		System.out.println(auteurSelection);
		return auteurSelection;
	}

	public static char askExistanceAuteur() {
		System.out.println("L'auteur du livre existe-t-il déjà dans la base de donnée? : Y / N");
		return Utils.readConfirmSelection();
	}
	
	public static int askIdAuteur() {
		System.out.println("Selectionnez l'id de l'auteur : ");
		return Utils.readInt();
	}

	public static char askChangeAuteur() {
		System.out.println("Vouls-vous éditer l'auteur ? : ");
		return Utils.readConfirmSelection();
	}
}
