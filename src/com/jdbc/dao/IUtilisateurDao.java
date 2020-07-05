package com.jdbc.dao;

import java.util.List;

import com.librairie.model.personne.Utilisateur;

public interface IUtilisateurDao extends Dao<Utilisateur> {

	public void createDemandeCompte(Utilisateur pObject);

	public List<Utilisateur> getAllDemandeCompte();

	public Utilisateur getByLogin(Utilisateur pObjet);

	public void deleteCompteValidation(Utilisateur pObjet);

}
