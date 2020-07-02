package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.dao.ICompteDao;
import com.librairie.model.compte.Compte;

public class CompteDaoImpl implements ICompteDao {
	Connection connection = DatabaseConnection.getInstance().getConnection();
	PreparedStatement ps = null;

	@Override
	public void create(Compte pObbject) {

	}

	@Override
	public List<Compte> readAll() {
		return null;
	}

	@Override
	public void update(Compte pObject) {

	}

	@Override
	public void delete(Compte pObject) {

	}

	@Override
	public Compte getByLogin(Compte pObjet) {
		try {
			ps = connection.prepareStatement("select login from compteutilisateur where login =?;");
			ps.setString(1, pObjet.getLogin());

			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				pObjet = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pObjet;
	}

}
