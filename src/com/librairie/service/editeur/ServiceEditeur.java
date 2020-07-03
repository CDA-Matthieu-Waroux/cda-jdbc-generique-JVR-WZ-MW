package com.librairie.service.editeur;

import java.util.List;

import com.jdbc.dao.IEditeurDao;
import com.jdbc.dao.bdd.EditeurDaoImpl;
import com.librairie.model.personne.Editeur;

public class ServiceEditeur {

	public static List<Editeur> getEditeurs() {
		IEditeurDao editeurDao = new EditeurDaoImpl();
		List<Editeur> editeurs = editeurDao.readAll();
		for (Editeur e : editeurs) {
			System.out.println(e);
		}
		return editeurs;
	}
}

