package com.jdbc.dao;

import com.librairie.model.personne.Utilisateur;

public interface IUtilisateurDao extends Dao<Utilisateur> {

	public void createDemandeCompte(Utilisateur pObject);

	public void getAllDemandeCompte(Utilisateur pObject);

	public Utilisateur getByLogin(Utilisateur pObjet);

}
