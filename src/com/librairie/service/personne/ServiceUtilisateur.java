package com.librairie.service.personne;

import java.util.Scanner;

import com.jdbc.dao.IUtilisateurDao;
import com.jdbc.dao.bdd.UtilisateurDaoImpl;
import com.librairie.model.commande.Adresse;
import com.librairie.model.compte.Compte;
import com.librairie.model.compte.TypeCompte;
import com.librairie.model.personne.Utilisateur;
import com.librairie.service.compte.ServiceCompte;
import com.librairie.utils.Utils;

public class ServiceUtilisateur {
	private static Utilisateur vUtilisateur = new Utilisateur();
	private static Scanner sc = new Scanner(System.in);
	private static Compte vCompte = new Compte();
	private static Adresse vAdresse = new Adresse();
	private static IUtilisateurDao dao = new UtilisateurDaoImpl();

	public static Utilisateur connection() {

		System.out.println("Veuillez saisir votre identifiant :");

		String tempo = sc.nextLine();
		vCompte.setLogin(tempo);
		vUtilisateur.setCp(vCompte);

		vUtilisateur = dao.getByLogin(vUtilisateur);

		if (vUtilisateur != null) {
			System.out.println("Veuillez saisir votre mot de passe");
			tempo = sc.nextLine();

			if (vUtilisateur.getCp().getPassword().equals(tempo)) {
				System.out.println("Connexion reussis !");

			} else {
				vUtilisateur = null;
				System.out.println("Mot de passe incorrect !");
			}

		} else {
			vUtilisateur = null;
			System.out.println("Erreur compte inconnus !");
		}
		return vUtilisateur;

	}

	public static void inscription() {
		boolean vVerif = true;
		int tempo2 = 0;
		String tempo = "";
		byte tempo3 = 2;
		System.out.println("Veuillez saisir votre prénom");
		tempo = sc.nextLine();

		vUtilisateur.setPrenom(tempo);
		System.out.println("Veuillez saisir votre nom");
		tempo = sc.nextLine();
		vUtilisateur.setNom(tempo);
		System.out.println("Veuillez saisir votre age");
		tempo2 = sc.nextByte();
		vUtilisateur.setAge(tempo3);
		System.out.println("Veuillez saisir votre n° de rue");
		tempo2 = Utils.readInt();
		vAdresse.setNumero(tempo2);
		System.out.println("Veuillez saisir votre rue");
		tempo = sc.nextLine();
		vAdresse.setRue(tempo);
		System.out.println("Veuillez saisir votre ville");
		tempo = sc.nextLine();
		vAdresse.setVille(tempo);
		System.out.println("Veuillez saisir votre code postal");
		tempo = sc.nextLine();
		vAdresse.setCodePostal(tempo);
		System.out.println("Veuillez saisir votre pays");
		tempo = sc.nextLine();
		vAdresse.setPays(tempo);
		while (vVerif) {
			System.out.println("Veuillez saisir votre login");
			tempo = sc.nextLine();
			vCompte.setLogin(tempo);
			vCompte = ServiceCompte.verifLogin(vCompte);
			if (vCompte == null) {
				vCompte = new Compte();
				vCompte.setLogin(tempo);
				vVerif = false;
			}
		}

		System.out.println("Veuillez saisir votre mot de passe");
		tempo = sc.nextLine();
		vCompte.setPassword(tempo);
		System.out.println("Compte Libraire ? (Y/N)");
		char c = Utils.readConfirmSelection();
		if (c == 'Y') {
			vCompte.setType(TypeCompte.LIBRAIRE);
		} else {
			vCompte.setType(TypeCompte.CLIENT);
		}

		vUtilisateur.setCp(vCompte);
		vUtilisateur.setAdresse(vAdresse);
		dao.createDemandeCompte(vUtilisateur);
		System.out.println(
				"Votre demande de création de compte a bien été pris en compte !\n Veuillez attendre la confirmation du libraire pour vous connecter");
	}

}
