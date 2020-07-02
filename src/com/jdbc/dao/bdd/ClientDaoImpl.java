package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jdbc.dao.IClientDao;
import com.librairie.model.personne.Client;

public class ClientDaoImpl implements IClientDao {

	Connection connection = DatabaseConnection.getInstance().getConnection();
	PreparedStatement ps = null;

	@Override
	public void create(Client pObbject) {

	}

	@Override
	public List<Client> readAll() {
		return null;
	}

	@Override
	public void update(Client pObject) {

	}

	@Override
	public void delete(Client pObject) {

	}

	@Override
	public int getIdClient(int idCompte) {
		int id = 0;
		String query = "SELECT id_client FROM client WHERE id_compte=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idCompte);
			ResultSet rs = ps.executeQuery();

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
