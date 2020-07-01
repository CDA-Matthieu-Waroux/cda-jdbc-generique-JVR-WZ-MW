package com.librairie.menu;

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

				break;

			case 2:

				break;
			case 3:

				break;

			case 0:
				continuer = false;
				break;

			default:
				break;
			}

		}
	}

	public static void menu() {
		System.out.println("1- S'authentifier");
		System.out.println("2- Lister les livres");
		System.out.println("3- Inscription");
		System.out.println("0- Exit");
	}
}
