package com.jdbc.dao;

import java.util.List;

import com.librairie.model.commande.Commande;

public abstract class ICommandeDao implements Dao<Commande> {
	@Override
	public void create(Commande pObbject) {

	}

	@Override
	public List<Commande> readAll() {
		return null;
	}

	@Override
	public void update(Commande pObject) {

	}

	@Override
	public void delete(Commande pObject) {

	}
}
