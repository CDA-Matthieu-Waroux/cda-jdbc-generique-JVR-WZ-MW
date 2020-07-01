package com.librairie.exer;

import java.sql.Connection;
import java.sql.SQLException;

import com.jdbc.dao.bdd.DatabaseConnection;

public class Program {
	public static void main(String[] args) {
		Connection c = null;
		try {
			c = DatabaseConnection.getInstance().getConnection();
			System.out.println("connecte");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
