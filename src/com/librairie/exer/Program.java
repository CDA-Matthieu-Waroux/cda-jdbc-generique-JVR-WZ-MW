package com.librairie.exer;

import java.sql.Connection;

import com.jdbc.dao.bdd.DatabaseConnection;
import com.librairie.menu.Menu;
import com.librairie.utils.Utils;
import com.utils.input.utilisateur.UtilsAuteur;

public class Program {
	public static Connection c = DatabaseConnection.getInstance().getConnection();

	public static void main(String[] args) {
		Menu.init();

	}
}
