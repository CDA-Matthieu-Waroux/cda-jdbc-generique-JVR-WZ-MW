package com.jdbc.dao.bdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnection {

	// 1 mariadb - 2 mysql
	private static int choixSgbd = 2;
	private static DatabaseConnection databaseconnection;
	private BasicDataSource ds;
	private Properties properties = new Properties();
	private FileInputStream fis;

	private DatabaseConnection() {
		try {
			fis = new FileInputStream("src/jdbc.properties");
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ds = new BasicDataSource();
		switch (choixSgbd) {
		case 1:
			ds.setDriverClassName(properties.getProperty("MARIA_DB_DRIVER_CLASS"));
			ds.setUrl(properties.getProperty("MARIA_DB_URL"));
			ds.setUsername(properties.getProperty("MARIA_DB_USERNAME"));
			ds.setPassword(properties.getProperty("MARIA_DB_PASSWORD"));
			break;
		case 2:
			ds.setDriverClassName(properties.getProperty("MYSQL_DB_DRIVER_CLASS"));
			ds.setUrl(properties.getProperty("MYSQL_DB_URL"));
			ds.setUsername(properties.getProperty("MYSQL_DB_USERNAME"));
			ds.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
			break;
		}

		// definit le nombre minimum de connexions inactives
		ds.setMinIdle(5);

		// renvoie le nombre maximal de connexions pouvant rester inactives dans le
		// pool.
		ds.setMaxIdle(10);

		// le nombre maximum de declaration ouvertes
		ds.setMaxOpenPreparedStatements(100);

	}

	public static DatabaseConnection getInstance() {
		if (databaseconnection == null) {
			databaseconnection = new DatabaseConnection();
			return databaseconnection;
		}
		return databaseconnection;
	}

	public Connection getConnection() {
		try {
			return this.ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
