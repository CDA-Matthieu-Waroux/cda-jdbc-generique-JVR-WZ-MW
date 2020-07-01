package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.ILivreDao;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Libraire;

public class LivreDaoImpl implements ILivreDao{
	
	Connection connection = DatabaseConnection.getInstance().getConnection();

	@Override
	public void create(Livre pObbject) {
		
	}

	@Override
	public List<Livre> readAll() {
		List<Livre> livres = new ArrayList<Livre>();
		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM livre")) {
			Livre tempLivre = new Livre();
			while (result.next()) {
				tempLivre.setRef(result.getInt("reference"));
				tempLivre.setNombrePage(result.getInt("nombrePage"));
				tempLivre.setPrix(result.getInt("prix"));
				tempLivre.setQuantitee(result.getInt("quantitee"));
				tempLivre.setTitre(result.getString("titre"));
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
