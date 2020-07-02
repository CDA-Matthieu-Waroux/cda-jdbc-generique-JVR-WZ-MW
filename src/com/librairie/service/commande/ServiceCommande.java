package com.librairie.service.commande;

import com.jdbc.dao.bdd.CommandeDaoImpl;
import com.librairie.model.commande.StatusCommande;
import com.librairie.service.personne.ServiceUtilisateur;
import com.librairie.utils.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCommande {

	static byte status;
	static int id;

	public static void creerCmd() {
		CommandeDaoImpl cmdDao = new CommandeDaoImpl();
		String sql = "INSERT INTO commande(id_status_commande,id_client) values (?,?)";
		status = StatusCommande.ENCOURS.getNumero();
		id = ServiceUtilisateur.getIdCompte();
		cmdDao.create(sql, status, id);

		System.out.println("Vous pouvez entrer la ref du livre");
		int ref = Utils.readInt();

		String sql1 = "Select * from livre where ref =?";
		cmdDao.queryForLivre(sql1, ref);

		System.out.println("Confirmer pour ajouter ce livre");
		char confirmation = Utils.readConfirmSelection();
		if (confirmation == 'Y') {
			System.out.println("Entrer la quantite que vous voulez commander pour ce livre");
			int qty = Utils.readInt();
		}
	}

}
