package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.IAuteurDao;
import com.librairie.model.personne.Auteur;

public class AuteurDaoImpl implements IAuteurDao {

	Connection connection = DatabaseConnection.getInstance().getConnection();

	@Override
	public void create(Auteur pObject) {
		String query = "call AddAuteur(?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, pObject.getPrenom());
			statement.setString(2, pObject.getNom());
			statement.setByte(3, pObject.getAge());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Auteur> readAll() {
		List<Auteur> auteurs = new ArrayList<>();
		String query = "select a.id_auteur, a.nom, a.prenom, a.age, a.id_adresse from auteur a";
		try (Statement statement = connection.createStatement(); ResultSet result = statement.executeQuery(query)) {
			while (result.next()) {
				Auteur tempAuteur = new Auteur();
				tempAuteur.setId(result.getInt("a.id_auteur"));
				tempAuteur.setNom(result.getString("a.nom"));
				tempAuteur.setPrenom(result.getString("a.prenom"));
				tempAuteur.setAge(result.getByte("a.age"));
				tempAuteur.setAdresse(null);
				auteurs.add(tempAuteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auteurs;
	}

	@Override
	public void update(Auteur pObject) {
	}

	@Override
	public void delete(Auteur pObject) {
	}
}
