package com.librairie.service.personne;

import java.util.List;
import java.util.Scanner;

import com.jdbc.dao.IAdresseDao;
import com.jdbc.dao.IUtilisateurDao;
import com.jdbc.dao.bdd.AdresseDaoImpl;
import com.jdbc.dao.bdd.UtilisateurDaoImpl;
import com.librairie.model.commande.Adresse;
import com.librairie.model.compte.Compte;
import com.librairie.model.compte.TypeCompte;
import com.librairie.model.personne.Utilisateur;
import com.librairie.service.commande.ServiceAdresse;
import com.librairie.service.compte.ServiceCompte;
import com.librairie.utils.BCrypt;
import com.librairie.utils.Hashage;
import com.librairie.utils.Utils;
import com.utils.input.utilisateur.UtilsAdresse;

public class ServiceUtilisateur {
	private static Utilisateur vUtilisateur = new Utilisateur();
	private static Scanner sc = new Scanner(System.in);
	private static Compte vCompte = new Compte();
	private static Adresse vAdresse = new Adresse();
	private static IUtilisateurDao daoUtilisateur = new UtilisateurDaoImpl();
	private static int idCompte = 0;
	private static List<Utilisateur> listeUtilisateurs;
	private static IAdresseDao daoAdresse = new AdresseDaoImpl();

	public static Utilisateur connection() {

		System.out.println("Veuillez saisir votre identifiant :");

		String tempo = sc.nextLine();
		vCompte.setLogin(tempo);
		vUtilisateur = new Utilisateur();
		vUtilisateur.setCp(vCompte);

		vUtilisateur = daoUtilisateur.getByLogin(vUtilisateur);

		if (vUtilisateur != null) {
			System.out.println("Veuillez saisir votre mot de passe");
			tempo = sc.nextLine();

			if (BCrypt.checkpw(tempo, vUtilisateur.getCp().getPassword())) {
				System.out.println(String.format("Bienvenue %s %s", vUtilisateur.getNom(), vUtilisateur.getPrenom()));
				idCompte = vUtilisateur.getCp().getIdCompte();
			} else {
				vUtilisateur = null;
				System.out.println("Mot de passe incorrect !");
			}

		} else {
			vUtilisateur = null;
			System.out.println("Erreur , le login n'hesite pas  !");
		}
		return vUtilisateur;

	}

	public static void inscription() {
		boolean vVerif = true;
		String tempo = "";
		byte tempo3 = 2;
		System.out.println("Veuillez saisir votre prénom");
		tempo = sc.nextLine().toUpperCase();
		vUtilisateur.setPrenom(tempo);
		System.out.println("Veuillez saisir votre nom");
		tempo = sc.nextLine().toUpperCase();
		vUtilisateur.setNom(tempo);
		System.out.println("Veuillez saisir votre age");
		tempo3 = Utils.readByte();
		vUtilisateur.setAge(tempo3);
		vAdresse = UtilsAdresse.askAdresse();
		sc.nextLine();
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
		tempo = Hashage.hash(tempo);
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
		daoUtilisateur.createDemandeCompte(vUtilisateur);
		System.out.println(
				"Votre demande de création de compte a bien été pris en compte !\n Veuillez attendre la confirmation du libraire pour vous connecter");
	}

	public static int getIdCompte() {
		return idCompte;
	}

	public static void validationCompteValidation() {
		listeUtilisateurs = daoUtilisateur.getAllDemandeCompte();
		afficherListe(listeUtilisateurs);
		System.out.println("Selectionnez l'index à valider");
		int i = 0;
		do {
			System.out.println("Selectionnez l'index à supprimer");
			i = Utils.readInt();
		}

		while (i < 0 | i > listeUtilisateurs.size());

		vUtilisateur = listeUtilisateurs.get(i);
		vAdresse = vUtilisateur.getAdresse();
		daoUtilisateur.create(vUtilisateur);
		if (ServiceAdresse.verifAdresse(vAdresse) == null) {
			daoAdresse.create(vAdresse);
			daoAdresse.LiaisonAdresse();
		} else {
			vAdresse = ServiceAdresse.verifAdresse(vAdresse);
			daoAdresse.LiaisonAdresse(vAdresse);
		}

		System.out.println("Le compte a bien été validé!");
		daoUtilisateur.deleteCompteValidation(vUtilisateur);

	}

	public static void suppressionCompteValidation() {
		listeUtilisateurs = daoUtilisateur.getAllDemandeCompte();
		afficherListe(listeUtilisateurs);

		int i = 0;
		do {
			System.out.println("Selectionnez l'index à supprimer");
			i = Utils.readInt();
		}

		while (i < 0 | i > listeUtilisateurs.size());
		vUtilisateur = listeUtilisateurs.get(i);
		daoUtilisateur.deleteCompteValidation(vUtilisateur);
		System.out.println("Demande bien supprimer");

	}

	private static void afficherListe(List<Utilisateur> list) {

		for (int i = 0; i < list.size(); i++) {
			System.out.println("index :" + i + " " + list.get(i));
		}
	}

}
