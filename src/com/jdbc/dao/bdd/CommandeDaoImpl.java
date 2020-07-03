package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.ICommandeDao;
import com.librairie.model.commande.Commande;
import com.librairie.model.livre.Livre;

public class CommandeDaoImpl extends ICommandeDao {
	Connection connection = DatabaseConnection.getInstance().getConnection();
	PreparedStatement preparedStatement = null;

	public void update(String sql, Object... args) {
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

	public Livre queryForLivre(String sql, int ref) {
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ref);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int reference = resultSet.getInt("reference");
				int prix = resultSet.getInt("prix");
				String titre = resultSet.getString("titre");
				Livre livre = new Livre(reference, titre, prix);

				return livre;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getResult(String sql, Object... args) {
		int id = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
