package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jdbc.dao.ILivreDao;
import com.librairie.model.commande.Adresse;
import com.librairie.model.livre.Livre;
import com.librairie.model.personne.Auteur;
import com.librairie.model.personne.Editeur;

public class LivreDaoImpl implements ILivreDao {

	Connection connection = DatabaseConnection.getInstance().getConnection();

	@Override
	public void create(Livre pObject) {
		// _id_auteur, _id_editeur, _titre, _nombre_page, _prix, _quantitee
		String query = "call AddLivre(?,?,?,?,?,?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, pObject.getAuteur().getId());
			statement.setInt(2, pObject.getEditeur().getIdEditeur());
			statement.setString(3, pObject.getTitre());
			statement.setInt(4, pObject.getNombrePage());
			statement.setInt(5, pObject.getPrix());
			statement.setInt(6, pObject.getQuantitee());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Livre> readAll() {
		
		String query = "SELECT l.REFERENCE, l.NOMBREPAGE, l.PRIX, l.TITRE, l.ID_EDITEUR, l.ID_AUTEUR, l.QUANTITEE, " + 
					   "a.NOM, a.PRENOM, a.AGE, a.ID_AUTEUR, a.ID_ADRESSE, " + 
					   "e.NOM, e.ID_EDITEUR, e.ID_ADRESSE, " + 
					   "ad.ID_ADRESSE, ad.CP, ad.NUMERO, ad.PAYS, ad.RUE, ad.VILLE " + 
					   "FROM LIVRE AS l " + 
					   "JOIN AUTEUR A ON l.ID_AUTEUR = a.ID_AUTEUR " + 
					   "JOIN EDITEUR E ON l.ID_EDITEUR = e.ID_EDITEUR " + 
					   "JOIN ADRESSE Ad on e.ID_ADRESSE = ad.ID_ADRESSE ";
		
		List<Livre> livres = new ArrayList<>();
		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(query)) {
			while (result.next()) {
				Livre tempLivre = new Livre();
				Auteur tempAuteur = new Auteur();
				Editeur tempEditeur = new Editeur();
				Adresse tempAdresse = new Adresse();
				
				tempAdresse.setNumero(result.getInt("ad.NUMERO"));
				tempAdresse.setRue(result.getString("ad.RUE"));
				tempAdresse.setVille(result.getString("ad.VILLE"));
				tempAdresse.setCodePostal(result.getString("ad.CP"));
				tempAdresse.setPays(result.getString("ad.PAYS"));
				
				tempAuteur.setNom(result.getString("a.NOM"));
				tempAuteur.setPrenom(result.getString("a.PRENOM"));
				tempAuteur.setAge(result.getByte("a.AGE"));
				tempAuteur.setId(result.getInt("a.ID_AUTEUR"));
				
				tempEditeur.setIdEditeur(result.getInt("e.ID_EDITEUR"));
				tempEditeur.setNom(result.getString("e.NOM"));
				tempEditeur.setAdresse(tempAdresse);
				
				tempLivre.setNombrePage(result.getInt("l.NOMBREPAGE"));
				tempLivre.setPrix(result.getInt("l.PRIX"));
				tempLivre.setRef(result.getInt("l.REFERENCE"));
				tempLivre.setQuantitee(result.getInt("l.QUANTITEE"));
				tempLivre.setTitre(result.getString("l.TITRE"));
				tempLivre.setAuteur(tempAuteur);
				tempLivre.setEditeur(tempEditeur);
				livres.add(tempLivre);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(livres);
		return livres;
	}

	@Override
	public void update(Livre pObject) {
		String query = "UPDATE livre set "
					 + "livre.NOMBREPAGE = (?), " // 1
					 + "livre.PRIX = (?), " // 2
					 + "livre.TITRE = (?), " // 3
					 + "livre.QUANTITEE = (?), " // 4
					 + "livre.ID_AUTEUR = (?), " // 5
					 + "livre.ID_EDITEUR = (?) " // 6
					 + "where livre.REFERENCE = (?)"; // 7
		try (PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, pObject.getNombrePage());
			statement.setInt(2, pObject.getPrix());
			statement.setString(3, pObject.getTitre());
			statement.setInt(4, pObject.getQuantitee());
			statement.setInt(5, pObject.getAuteur().getId());
			statement.setInt(6, pObject.getEditeur().getIdEditeur());
			statement.setInt(7, pObject.getRef());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateQuantitee(Livre pObject) {
		String query = "DELETE FROM livre WHERE livre.reference = (?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, pObject.getReference());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Livre pObject) {
		String query = "DELETE FROM livre WHERE livre.reference = (?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, pObject.getReference());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
