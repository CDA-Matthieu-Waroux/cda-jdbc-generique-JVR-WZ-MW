package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.dao.IUtilisateurDao;
import com.librairie.model.personne.Utilisateur;

public class UtilisateurDaoImpl implements IUtilisateurDao {
	Connection connection = DatabaseConnection.getInstance().getConnection();

	@Override
	public void create(Utilisateur pObbject) {

		try {
			PreparedStatement ps = connection.prepareStatement("call AjoutCompteValidation(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, pObbject.getLogin());
			ps.setString(2, pObbject.getPassword());
			ps.setString(3, pObbject.getNom());
			ps.setString(4, pObbject.getPrenom());
			ps.setByte(5, pObbject.getAge());
			ps.setInt(6, pObbject.getAdresse().getNumero());
			ps.setString(7, pObbject.getAdresse().getRue());
			ps.setString(8, pObbject.getAdresse().getCodePostal());
			ps.setString(9, pObbject.getAdresse().getVille());
			ps.setString(10, pObbject.getAdresse().getPays());

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void createDemandeCompte(Utilisateur pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllDemandeCompte(Utilisateur pObject) {
		// TODO Auto-generated method stub

	}

}
