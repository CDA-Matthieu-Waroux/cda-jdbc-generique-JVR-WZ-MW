package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
