package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.dao.IUtilisateurDao;
import com.librairie.model.compte.Compte;
import com.librairie.model.compte.TypeCompte;
import com.librairie.model.personne.Utilisateur;

public class UtilisateurDaoImpl implements IUtilisateurDao {
	Connection connection = DatabaseConnection.getInstance().getConnection();
	PreparedStatement ps = null;
	Compte compte = new Compte();

	@Override
	public void create(Utilisateur pObbject) {

	}

	@Override
	public List<Utilisateur> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utilisateur pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur pObject) {

	}

	@Override
	public void createDemandeCompte(Utilisateur pObject) {

		try {
			ps = connection.prepareStatement("call AjoutCompteValidation(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, pObject.getCp().getLogin());
			ps.setString(2, pObject.getCp().getPassword());
			ps.setString(3, pObject.getNom());
			ps.setString(4, pObject.getPrenom());
			ps.setByte(5, pObject.getAge());
			ps.setInt(6, pObject.getAdresse().getNumero());
			ps.setString(7, pObject.getAdresse().getRue());
			ps.setString(8, pObject.getAdresse().getCodePostal());
			ps.setString(9, pObject.getAdresse().getVille());
			ps.setString(10, pObject.getAdresse().getPays());
			ps.setByte(11, pObject.getCp().getType().getNumero());

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void getAllDemandeCompte(Utilisateur pObject) {

	}

	@Override
	public Utilisateur getByLogin(Utilisateur pObjet) {

		try {
			ps = connection.prepareStatement(
					"select compteutilisateur.login ,compteutilisateur.password,compteutilisateur.id_compte , typecompte.libele \r\n"
							+ "from compteutilisateur \r\n" + "natural join typecompte \r\n" + "where login =?;");

			ps.setString(1, pObjet.getCp().getLogin());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				compte.setLogin(rs.getString("login"));
				compte.setPassword(rs.getString("password"));
				compte.setIdCompte(rs.getInt("id_compte"));

				if (rs.getString("libele").equalsIgnoreCase("Client")) {
					compte.setType(TypeCompte.CLIENT);
				} else if (rs.getString("libele").equalsIgnoreCase("Libraire")) {
					compte.setType(TypeCompte.LIBRAIRE);
				}

				pObjet.setCp(compte);
			} else {
				pObjet = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pObjet;
	}

}
