package com.librairie.service.personne;

import java.util.Scanner;

import com.jdbc.dao.IUtilisateurDao;
import com.jdbc.dao.bdd.UtilisateurDaoImpl;
import com.librairie.model.compte.Compte;
import com.librairie.model.personne.Utilisateur;

public class ServiceUtilisateur {
	private static Utilisateur vUtilisateur = new Utilisateur();
	private static Scanner sc = new Scanner(System.in);
	private static Compte vCompte = new Compte();

	public static Utilisateur connection() {
		IUtilisateurDao dao = new UtilisateurDaoImpl();

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

}
