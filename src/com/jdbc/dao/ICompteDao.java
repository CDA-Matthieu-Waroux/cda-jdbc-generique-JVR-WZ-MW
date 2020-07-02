package com.jdbc.dao;

import com.librairie.model.compte.Compte;

public interface ICompteDao extends Dao<Compte> {

	public Compte getByLogin(Compte pObjet);
}
