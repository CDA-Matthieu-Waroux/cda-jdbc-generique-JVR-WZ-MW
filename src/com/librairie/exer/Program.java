package com.librairie.exer;

import java.sql.Connection;
import java.sql.SQLException;

import com.jdbc.dao.Dao;
import com.jdbc.dao.ILivreDao;
import com.jdbc.dao.bdd.DatabaseConnection;
import com.jdbc.dao.bdd.LivreDaoImpl;

public class Program {
	public static void main(String[] args) {
		Connection c = null;
		c = DatabaseConnection.getInstance().getConnection();
		System.out.println("connecte");
	}
}
