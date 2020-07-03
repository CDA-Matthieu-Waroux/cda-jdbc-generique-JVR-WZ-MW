package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		String sql = "select commande.numero_commande ,commande.date_commande,sum((composer.quantitee * livre.prix)) as prix_total, compteutilisateur.nom ,compteutilisateur.prenom ,statuscommande.libele_status_commande \n"
				+ "from composer\n" + "natural join  commande\n"
				+ "inner join livre on livre.reference =composer.reference\n"
				+ "inner join compteutilisateur on compteutilisateur.id_compte =commande.id_compte\n"
				+ "inner join statuscommande on statuscommande .id_status_commande  =commande .id_status_commande \n"
				+ "group by numero_commande;";
		List<Commande> commandes = new ArrayList<>();
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			Commande tempCommande = new Commande();

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (resultSet.next()) {
				tempCommande.setNumero_commande(resultSet.getInt("numero_commande"));
				tempCommande.setDate_commande(resultSet.getTimestamp("date_commande").toString());
				tempCommande.setPrix_total(resultSet.getBigDecimal("prix_total"));
				tempCommande.setNom(resultSet.getString("nom"));
				tempCommande.setPrenom(resultSet.getString("prenom"));
				tempCommande.setLibele_status_commande(resultSet.getString("libele_status_commande"));
				commandes.add(tempCommande);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
