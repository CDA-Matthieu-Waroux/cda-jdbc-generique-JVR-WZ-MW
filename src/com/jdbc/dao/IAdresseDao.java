package com.jdbc.dao;

import com.librairie.model.commande.Adresse;

public interface IAdresseDao extends Dao<Adresse> {
	public Adresse getAdresse(Adresse pObjet);
}
