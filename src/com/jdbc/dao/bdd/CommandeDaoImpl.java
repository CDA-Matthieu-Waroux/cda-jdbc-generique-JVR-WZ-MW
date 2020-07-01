package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.ICommandeDao;
import com.librairie.model.commande.Commande;
import com.librairie.utils.Utils;

public class CommandeDaoImpl extends ICommandeDao {
	Connection connection = DatabaseConnection.getInstance().getConnection();

	public void create(String sql, Object... args) {
		PreparedStatement preparedStatement = null;
		try {
			String sql = "INSERT INTO commande(numeroCmd,id_status_commande,id_client) values (?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeResource(connection, preparedStatement);
		}
	}

	@Override
	public List<Commande> readAll() {
		List<Commande> commandes = new ArrayList<>();
		try (Statement statement = connection.createStatement();
				ResultSet result = statement
						.executeQuery("SELECT * FROM commande INNER JOIN comprendre ON numeroCmd")) {
			Commande tempCommande = new Commande();
			while (result.next()) {
				tempCommande.setRef(result.getInt("reference"));
				tempCommande.setNombrePage(result.getInt("nombrePage"));
				tempCommande.setPrix(result.getInt("prix"));
				tempCommande.setQuantitee(result.getInt("quantitee"));
				tempCommande.setTitre(result.getString("titre"));
				commandes.add(tempCommande);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}

}
