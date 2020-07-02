package com.librairie.service.livre;

//ILivreDao livreDao = new LivreDaoImpl();
//Adresse ad = new Adresse();
//Auteur a = new Auteur();
//Editeur e = new Editeur();
//Livre l = new Livre(30, 50, 600, 3, "java", a, e);
//ad.setCodePostal("59000");
//ad.setNumero(10);
//ad.setPays("france");
//ad.setRue("du 8 mai");
//ad.setVille("lille");
//a.setId(1);
//e.setIdEditeur(1);
//LivreDaoImpl ldi = new LivreDaoImpl();
// ldi.create(l);

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.auteur.service.ServiceAuteur;
import com.jdbc.dao.IAuteurDao;
import com.jdbc.dao.IEditeurDao;
import com.jdbc.dao.ILivreDao;
import com.jdbc.dao.bdd.AuteurDaoImpl;
import com.jdbc.dao.bdd.EditeurDaoImpl;
import com.jdbc.dao.bdd.LivreDaoImpl;
import com.librairie.model.commande.Adresse;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Editeur;
import com.librairie.service.commande.ServiceAdresse;
import com.librairie.service.editeur.ServiceEditeur;

public class ServiceLivre {

	public static Scanner sc = new Scanner(System.in);

	// envoie tous les livres dans la bdd
	public static void readLine() {
		ILivreDao livreDao = new LivreDaoImpl();
		List<Livre> livres = livreDao.readAll();
		for (Livre livre : livres) {
			System.out.println(livre);
		}
	}

	// créer un livre
	public static void createLivre() {
		
		// demande si l'editeur existe oui ou non
		char editeurExiste = askExistanceEditeur();
		Editeur editeurSelection = null;
		if(editeurExiste == 'o') {
			editeurSelection = choixAdresseEditeur();
		} else if (editeurExiste == 'n') {
			editeurSelection = createEditeur();
			// envoie le nouvel auteur créé dans la bdd
			
			IEditeurDao editeurDao = new EditeurDaoImpl();
			editeurDao.create(editeurSelection);
		}
		System.out.println("Editeur : " + editeurSelection);
		
		// demande si l'auteur existe oui ou non
		char auteurExiste = askExistanceAuteur();
		Auteur auteurSelection = null;
		if(auteurExiste == 'o') {
			auteurSelection = choixAuteur();
		} else if (auteurExiste == 'n') {
			auteurSelection = createAuteur();
			
			// envoie le nouvel auteur créé dans la bdd
			IAuteurDao auteurDao = new AuteurDaoImpl();
			auteurDao.create(auteurSelection);
		}
	}

	private static Auteur createAuteur() {
		List<Auteur> auteurs = ServiceAuteur.getAuteurs();
		String prenom = askPrenomAuteur();
		sc.next();
		String nom = askNomAuteur();
		sc.next();
		byte age = askAgeAuteur();
		int id = (auteurs.get(auteurs.size() - 1).getId()) + 1;
		Adresse adresse = null;
		return new Auteur(nom, prenom, age, id, adresse);
	}

	private static byte askAgeAuteur() {
		System.out.println("Entrer l'age de l'auteur : ");
		return sc.nextByte();
	}

	private static String askNomAuteur() {
		System.out.println("Entrer le nom de l'auteur : ");
		return sc.nextLine();
	}

	private static String askPrenomAuteur() {
		System.out.println("Entrer le prenom de l'auteur : ");
		return sc.nextLine();
	}

	private static Auteur choixAuteur() {
		List<Auteur> auteurs = ServiceAuteur.getAuteurs();
		Auteur auteurSelection = null;
		System.out.println("Choisissez un auteur par son id");
		while(auteurSelection == null) {
			try {
				auteurSelection = auteurs.get(askIdEditeur() - 1);
				
			} catch (InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Saisissez un nombre entre 1 et " + auteurs.get(auteurs.size()-1).getId());
			}
		}
		System.out.println(auteurSelection);
		return auteurSelection;
	}

	private static char askExistanceAuteur() {
		System.out.println("L'auteur du livre existe-t-il déjà dans la base de donnée? : o/n");
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

	private static Editeur createEditeur() {
		List<Editeur> editeurs = ServiceEditeur.getEditeurs();
		int idEditeur = (editeurs.get(editeurs.size()-1).getIdEditeur()) + 1;
		System.out.println("Le prochain editeur aura l'id n : " + idEditeur);
		String nomEditieur = askNomEditeur();
		Adresse adresse = askAdresse();
		return new Editeur(idEditeur, nomEditieur, adresse);
	}

	private static String askNomEditeur() {
		System.out.println("Quel est le nom de l'edition : ");
		sc.nextLine();
		String answer = sc.nextLine();
		return answer;
	}

	private static Adresse askAdresse() {
		List<Adresse> adresses = ServiceAdresse.getAdresses();
		int id = (adresses.get(adresses.size() - 1).getId() + 1);
		int numero = askAdresseNumero();
		String rue = askAdresseRue();
		String ville = askAdresseVille();
		String cp = askAdresseCp();
		String pays = askPays();
		return new Adresse(id, numero, rue, ville, cp, pays);
	}

	private static String askAdresseCp() {
		System.out.println("Quel est le code postal de votre ville ? : ");
		return sc.nextLine();
	}

	private static String askPays() {
		System.out.println("Quel pays habitez-vous ? : ");
		return sc.nextLine();
	}

	private static String askAdresseVille() {
		System.out.println("Dans quel ville habitez-vous ? : ");
		return sc.nextLine();
	}

	private static String askAdresseRue() {
		System.out.println("Dans quel rue habitez-vous ? : ");
		return sc.nextLine();
	}

	private static int askAdresseNumero() {
		System.out.println("Entrez le numero de maison : ");
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

	private static char askExistanceEditeur() {
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
	
	private static Editeur choixAdresseEditeur() {
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
	
	private static int askIdEditeur() {
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

	public static void main(String[] args) {
		createLivre();
	}
}
