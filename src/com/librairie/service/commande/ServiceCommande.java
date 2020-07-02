package com.librairie.service.commande;

import com.jdbc.dao.bdd.ClientDaoImpl;
import com.jdbc.dao.bdd.CommandeDaoImpl;
import com.librairie.model.commande.StatusCommande;
import com.librairie.service.personne.ServiceUtilisateur;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCommande {

	static byte status;
	static int id;

	public static void creerCmd() {
		CommandeDaoImpl cmdDao = new CommandeDaoImpl();
		ClientDaoImpl clientDao = new ClientDaoImpl();
		String sql = "INSERT INTO commande(id_status_commande,id_client) values (?,?)";
		status = StatusCommande.ENCOURS.getNumero();
		id = clientDao.getIdClient(ServiceUtilisateur.getIdCompte());
		cmdDao.create(sql, status, id);

	}

}
