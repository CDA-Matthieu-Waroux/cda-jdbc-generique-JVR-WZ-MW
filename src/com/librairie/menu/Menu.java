package com.librairie.menu;

import com.librairie.model.compte.TypeCompte;
import com.librairie.model.personne.Utilisateur;
import com.librairie.service.commande.ServiceCommande;
import com.librairie.service.livre.ServiceLivre;
import com.librairie.service.personne.ServiceUtilisateur;
import com.librairie.utils.Utils;

public class Menu {

	public static void init() {
		int choix = 0;
		boolean continuer = true;

		while (continuer) {
			menu();
			choix = Utils.readInt();
			switch (choix) {
			case 1:
				Utilisateur utilisateur = ServiceUtilisateur.connection();
				if (utilisateur != null) {

					if (utilisateur.getCp().getType().equals(TypeCompte.CLIENT)) {
						initClient();

					} else if (utilisateur.getCp().getType().equals(TypeCompte.LIBRAIRE)) {
						initLibraire();
					}
				}

				break;
			case 2:
				ServiceLivre.readLine();
				break;
			case 3:
				ServiceUtilisateur.inscription();
				break;
			case 0:
				continuer = false;
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				Utils.readReturn();
				break;
			}

		}
	}

	public static void menu() {
		System.out.println("1- S'authentifier");
		System.out.println("2- Lister livre");
		System.out.println("3- Inscription");
		System.out.println("0- Exit");
	}

	public static void initClient() {
		int choix = 0;
		boolean continuer = true;

		while (continuer) {
			menuClient();
			choix = Utils.readInt();
			switch (choix) {
			case 1:
				ServiceLivre.readLine();
				ServiceCommande.creerCmd();
				ServiceCommande.commanderLivre();
				break;
			case 2:
				ServiceCommande.listerCmdClient();
				break;
			case 3:
				ServiceCommande.listerCmdClient();
				ServiceCommande.annulerCmd();
				break;
			case 4:
				ServiceLivre.readLine();
				break;
			case 0:
				continuer = false;
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				Utils.readReturn();
				break;
			}

		}
	}

	public static void menuClient() {
		System.out.println("1- Commander");
		System.out.println("2- Lister commande client");
		System.out.println("3- Annuler commande");
		System.out.println("4- Lister livre");
		System.out.println("0- Deconnexion");
	}

	public static void initLibraire() {
		int choix = 0;
		boolean continuer = true;

		while (continuer) {
			menuLibraire();
			choix = Utils.readInt();
			switch (choix) {
			case 1:
				ServiceCommande.listerCmdLibraire();
				break;

			case 2:
				ServiceCommande.listerCmdLibraire();
				ServiceCommande.modifierEtatCmd();
				break;
			case 3:
				ServiceLivre.createLivre();
				break;

			case 4:
				ServiceLivre.updateQuantiteLivre();
				break;
			case 5:
				ServiceLivre.supprimerLivre();
				break;
			case 6:
				ServiceUtilisateur.validationCompteValidation();
				break;
			case 7:
				ServiceUtilisateur.suppressionCompteValidation();
				break;
			case 0:
				continuer = false;
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				Utils.readReturn();
				break;
			}

		}
	}

	public static void menuLibraire() {
		System.out.println("1- Lister commande de tous les clients");
		System.out.println("2- Modifier l'etat d'une commande");
		System.out.println("3- Ajouter un livre");
		System.out.println("4- Modifierla quantite d'un livre");
		System.out.println("5- Supprimer un livre");
		System.out.println("6- Valider la demande de creation d'un compte");
		System.out.println("7- Supprimer la demande de creation d'un compte");
		System.out.println("0- Deconnexion");
	}
}
