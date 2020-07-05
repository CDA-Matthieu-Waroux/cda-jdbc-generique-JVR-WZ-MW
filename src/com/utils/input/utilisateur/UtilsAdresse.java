package com.utils.input.utilisateur;

import java.util.List;
import java.util.Scanner;

import com.librairie.model.commande.Adresse;
import com.librairie.service.commande.ServiceAdresse;
import com.librairie.utils.Utils;

public class UtilsAdresse {

	private static Scanner sc = new Scanner(System.in);

	public static Adresse askAdresse() {
		List<Adresse> adresses = ServiceAdresse.getAdresses();
		int id = (adresses.get(adresses.size() - 1).getId() + 1);
		int numero = askAdresseNumero();
		String rue = askAdresseRue();
		String ville = askAdresseVille();
		String cp = askAdresseCp();
		String pays = askPays();
		return new Adresse(id, numero, rue, ville, cp, pays);
	}

	public static String askAdresseCp() {

		String f = "";

		do {
			System.out.println("Quel est le code postal de la ville  : ");
			f = sc.nextLine();

		} while (!(f.length() < 10 && f.matches("\\d+")));
		return f;
	}

	public static String askPays() {
		System.out.println("Quel est le pays ? : ");
		return sc.nextLine().toUpperCase();
	}

	public static String askAdresseVille() {
		System.out.println("Quel est la ville ? : ");
		return sc.nextLine().toUpperCase();
	}

	public static String askAdresseRue() {
		System.out.println("Quel est la rue  ? : ");
		return sc.nextLine().toUpperCase();
	}

	public static int askAdresseNumero() {
		System.out.println("Quel est le numero de rue ? : ");
		return Utils.readInt();
	}

}
