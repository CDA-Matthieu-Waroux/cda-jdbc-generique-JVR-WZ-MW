package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.ILivreDao;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Libraire;

public class LivreDaoImpl implements ILivreDao{
	
	Connection connection = DatabaseConnection.getInstance().getConnection();

	@Override
	public void create(Livre pObbject) {
		
	}

	@Override
	public List<Livre> readAll() {
		String query = "SELECT l.REFERENCE, l.NOMBREPAGE, l.PRIX, l.TITRE, l.ID_EDITEUR, l.ID_EDITEUR, l.QUANTITEE, l.ID_AUTEUR, a.NOM, a.PRENOM, a.AGE, a.ID_AUTEUR , "
				     + "e.NOM FROM LIVRE AS l JOIN AUTEUR A ON l.ID_AUTEUR = a.ID_AUTEUR JOIN EDITEUR E ON l.ID_EDITEUR = e.ID_EDITEUR;";
		List<Livre> livres = new ArrayList<Livre>();
		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(query)) {
			while (result.next()) {
				Livre tempLivre = new Livre();
				Auteur tempAuteur = new Auteur();
				
				tempAuteur.setId(result.getInt("a.ID_AUTEUR"));
				tempAuteur.setAge(result.getInt("a.AGE"));
				tempAuteur.setPrenom(result.getString("a.PRENOM"));
				tempAuteur.setNom(result.getString("a.NOM"));
				
				tempLivre.setTitre(result.getString("l.TITRE"));
				tempLivre.setRef(result.getInt("l.REFERENCE"));
				tempLivre.setPrix(result.getInt("l.PRIX"));
				tempLivre.setNombrePage(result.getInt("l.NOMBREPAGE"));
				tempLivre.setQuantitee(result.getInt("l.QUANTITEE"));
				tempLivre.setAuteur(tempAuteur);
				tempLivre.setEditeur(result.getString("e.NOM"));
				livres.add(tempLivre);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livres;
	}

	@Override
	public void update(Livre pObject) {
		
	}

	@Override
	public void delete(Livre pObject) {
		
	}

}
