package com.jdbc.dao.bdd;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.ICommandeDao;
import com.librairie.model.commande.Commande;
import com.librairie.model.livre.Livre;

public class CommandeDaoImpl extends ICommandeDao {
	Connection connection = DatabaseConnection.getInstance().getConnection();
	PreparedStatement preparedStatement = null;

	public void create(String sql, Object... args) {
		preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Commande> readAll() {
		List<Commande> commandes = new ArrayList<>();
//		try (Statement statement = connection.createStatement();
//				ResultSet result = statement
//						.executeQuery("SELECT * FROM commande INNER JOIN comprendre ON numeroCmd")) {
//			Commande tempCommande = new Commande();
//			while (result.next()) {
//				tempCommande.setRef(result.getInt("reference"));
//				tempCommande.setNombrePage(result.getInt("nombrePage"));
//				tempCommande.setPrix(result.getInt("prix"));
//				tempCommande.setQuantitee(result.getInt("quantitee"));
//				tempCommande.setTitre(result.getString("titre"));
//				commandes.add(tempCommande);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return commandes;
	}

	public Livre queryForLivre(String sql, Object... args) {
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();

			if (resultSet.next()) {
				Livre livre = new Livre();

				for (int i = 0; i < columnCount; i++) {
					Object columnValue = resultSet.getObject(i + 1);

					String columnName = resultSetMetaData.getColumnName(i + 1);

					Field field = Livre.class.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(livre, columnValue);
				}
				return livre;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
