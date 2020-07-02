package com.librairie.service.commande;

import java.util.List;

import com.jdbc.dao.IAdresseDao;
import com.jdbc.dao.bdd.AdresseDaoImpl;
import com.librairie.model.commande.Adresse;

public class ServiceAdresse {

	private static IAdresseDao dao = new AdresseDaoImpl();

	public static Adresse verifAdresse(Adresse pAdresse) {
		pAdresse = dao.getAdresse(pAdresse);

		return pAdresse;
	}
	
	public static List<Adresse> getAdresses() {
		List<Adresse> adresses = dao.readAll();
		return adresses;
	}
}
