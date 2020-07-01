package com.librairie.service.livre;

import java.util.List;

import com.jdbc.dao.ILivreDao;
import com.jdbc.dao.bdd.LivreDaoImpl;
import com.librairie.model.livre.Livre;

public class ServiceLivre {

	public static void readLine() {
		ILivreDao livreDao = new LivreDaoImpl();
		List<Livre> livres = livreDao.readAll();
		for (Livre livre : livres) {
			System.out.println(livre);
		}
	}
}
