package com.librairie.utils;

import java.util.Scanner;

public class Utils {
	private static Scanner scanner = new Scanner(System.in);

	private static String readKeyBoard(int limit, boolean blankReturn) {
		String line = "";

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			if (line.length() == 0) {
				if (blankReturn)
					return line;
				else
					continue;
			}

			if (line.length() < 1 || line.length() > limit) {
				System.out.print("Votre saisie n'est pas correct, veuillez resaissir : ");
				continue;
			}
			break;
		}

		return line;
	}

	public static char readConfirmSelection() {
		char c;
		for (;;) {
			String str = readKeyBoard(1, false).toUpperCase();
			c = str.charAt(0);
			if (c == 'Y' || c == 'N') {
				break;
			} else {
				System.out.print("Saisissez Y ou N:");
			}
		}
		return c;
	}

	public static int readInt() {
		int n;
		for (;;) {
			String str = readKeyBoard(9, false);
			try {
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				System.out.print("Le nombre n'est pas correct, veuillez resaissir : ");
			}
		}
		return n;
	}

	public static void readReturn() {
		System.out.print("Tapez enter pour continuer...");
		readKeyBoard(100, true);
	}

	public static byte readByte() {
		byte n;
		for (;;) {
			String str = readKeyBoard(3, false);
			try {
				n = Byte.parseByte(str);
				if (n < 16 || n > 127) {
					System.out.println("Votre age n'est pas correct. Vous devez etre entre 16-127 ans");
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.print("Le nombre n'est pas correct, veuillez resaissir : ");
			}
		}
		return n;
	}

}
