package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.IEditeurDao;
import com.librairie.model.commande.Adresse;
import com.librairie.model.personne.Editeur;

public class EditeurDaoImpl implements IEditeurDao {
	
	Connection connection = DatabaseConnection.getInstance().getConnection();

	@Override
	public void create(Editeur pObject) {
		String query = "INSERT INTO editeur(?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, pObject.getIdEditeur());
			statement.setString(2, pObject.getNom());
			statement.setInt(3, pObject.getAdresse().getId());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Editeur> readAll() {
		
		List<Editeur> editeurs = new ArrayList<>();
		String query = "SELECT e.NOM, e.ID_EDITEUR, e.ID_ADRESSE, " + 
					   "ad.CP, ad.ID_ADRESSE, ad.NUMERO, ad.PAYS, ad.RUE, ad.VILLE " + 
					   "FROM EDITEUR e " + 
					   "JOIN ADRESSE ad on e.ID_ADRESSE = ad.ID_ADRESSE";
		
		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(query)) {
			while (result.next()) {
				Editeur tempsEditeur = new Editeur();
				Adresse tempAdresse = new Adresse();
				
				tempAdresse.setCodePostal(result.getString("ad.CP"));
				tempAdresse.setNumero(result.getInt("ad.NUMERO"));
				tempAdresse.setPays(result.getString("ad.PAYS"));
				tempAdresse.setRue(result.getString("ad.RUE"));
				tempAdresse.setVille(result.getString("ad.VILLE"));
				
				tempsEditeur.setNom(result.getString("e.NOM"));
				tempsEditeur.setIdEditeur(result.getInt("e.ID_EDITEUR"));
				tempsEditeur.setAdresse(tempAdresse);
				editeurs.add(tempsEditeur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return editeurs;
	}

	@Override
	public void update(Editeur pObject) {
		
	}

	@Override
	public void delete(Editeur pObject) {
		
	}

}
