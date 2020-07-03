package com.librairie.exer;

import java.sql.Connection;

import com.jdbc.dao.bdd.DatabaseConnection;
import com.librairie.menu.Menu;

public class Program {
	public static void main(String[] args) {
		Connection c = null;
		c = DatabaseConnection.getInstance().getConnection();
		System.out.println("connecte");

		Menu.init();

	}
}
