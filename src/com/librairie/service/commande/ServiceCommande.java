package com.librairie.service.commande;

import com.jdbc.dao.bdd.CommandeDaoImpl;
import com.librairie.model.commande.StatusCommande;
import com.librairie.model.personne.Client;

public class ServiceCommande {

	static StatusCommande status;
	static int id;

	public static void creerCmd() {
		CommandeDaoImpl cmdDao = new CommandeDaoImpl();
		String sql = "INSERT INTO commande(id_status_commande,id_client) values (?,?)";
		status = StatusCommande.ENCOURS;
		Client client = new Client(); // j'ai besoin une method pour recupere l'id de client en cours
		id = client.getId();
		cmdDao.create(sql, status, id);
	}
}
