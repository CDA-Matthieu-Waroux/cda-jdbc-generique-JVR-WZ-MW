package com.librairie.exer;

import java.sql.Connection;
import java.sql.SQLException;

import com.jdbc.dao.bdd.DatabaseConnection;
import com.jdbc.dao.bdd.UtilisateurDaoImpl;
import com.librairie.model.commande.Adresse;
import com.librairie.model.personne.Utilisateur;

public class Program {
	public static void main(String[] args) {
		Connection c = null;
		UtilisateurDaoImpl util = new UtilisateurDaoImpl();
		try {
			c = DatabaseConnection.getInstance().getConnection();
			System.out.println("connecté");
			Adresse a1 = new Adresse(23, "rue du laboureur", "Wattrelos", "59150", "France");
			Utilisateur u1 = new Utilisateur("Matthieu", "Richard", (byte) 26, 2, a1, "login", "1234", 2);
			util.create(u1);
			System.out.println("utilisateur créer");

		} 
	}
}
