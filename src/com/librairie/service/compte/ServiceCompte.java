package com.librairie.service.compte;

import com.jdbc.dao.ICompteDao;
import com.jdbc.dao.bdd.CompteDaoImpl;
import com.librairie.model.compte.Compte;

public class ServiceCompte {
	private static ICompteDao dao = new CompteDaoImpl();

	public static Compte verifLogin(Compte pCompte) {
		pCompte = dao.getByLogin(pCompte);

		return pCompte;
	}

}
