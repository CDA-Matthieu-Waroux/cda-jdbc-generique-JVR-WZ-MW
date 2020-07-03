package com.librairie.service.commande;

import java.time.LocalDateTime;

import com.jdbc.dao.bdd.CommandeDaoImpl;
import com.librairie.model.commande.StatusCommande;
import com.librairie.model.livre.Livre;
import com.librairie.service.personne.ServiceUtilisateur;
import com.librairie.utils.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCommande {

	static byte status;
	static int id;
	static int lastId;

	public static void creerCmd() {

		CommandeDaoImpl cmdDao = new CommandeDaoImpl();
		String sql = "INSERT INTO commande(id_compte, id_status_commande,date_commande) values (?,?,?)";
		status = StatusCommande.ENCOURS.getNumero();
		id = ServiceUtilisateur.getIdCompte();
		cmdDao.update(sql, id, status, LocalDateTime.now().plusHours(2));

		String sql1 = "SELECT LAST_INSERT_ID()";
		lastId = cmdDao.getResult(sql1);
	}

	public static void commanderLivre() {
		CommandeDaoImpl cmdDao = new CommandeDaoImpl();
		System.out.println("Vous pouvez commencer a commander des livres.");

		boolean continuer = true;
		int choix;
		int choix1;

		while (continuer) {
			menu();
			choix = Utils.readInt();

			switch (choix) {
			case 1:

				int exist = 0;
				System.out.println("Entrez la reference du livre");
				int ref = Utils.readInt();

				String sql2 = "select count(reference) from composer where numero_commande =? and reference=?";
				exist = cmdDao.getResult(sql2, lastId, ref);

				String sql3 = "select quantitee from livre where reference =?";
				int qtyLivre = cmdDao.getResult(sql3, ref);

				String sql8 = "update livre set quantitee =? where reference =?";

				if (exist != 0) {
					System.out.println("Ce livre est deja dans votre commande");
					System.out.println("1- Modifier la quantite");
					System.out.println("0- Retour");
					choix1 = Utils.readInt();
					switch (choix1) {
					case 1:
						System.out.println("Entrez la nouvelle quantite pour ce livre");
						int newQty = Utils.readInt();

						if (newQty <= 0) {
							System.out.println("La quantite minimum est 1");
						} else if (qtyLivre >= newQty) {
							String sql4 = "update composer set quantitee =? where numero_commande = ?";
							cmdDao.update(sql4, newQty, lastId);
							System.out.println("La quantite est modifiee");

							cmdDao.update(sql8, qtyLivre - newQty, ref);
						} else {
							System.out.println("Le quantite maximum est " + qtyLivre);
						}
						break;
					case 0:
						break;
					}
				} else {
					String sql5 = "Select * from livre where reference =?";
					Livre livre = cmdDao.queryForLivre(sql5, ref);
					System.out.println("----------------------\nRef : " + livre.getRef() + "\nTitre : "
							+ livre.getTitre() + "\nPrix : " + livre.getPrix() + "\n----------------------");

					System.out.println("Confirmer pour ajouter ce livre. Y ou N");
					char confirmation = Utils.readConfirmSelection();
					if (confirmation == 'Y') {
						System.out.println("Entrer la quantite que vous voulez commander pour ce livre");
						int qty = Utils.readInt();

						if (qty <= 0) {
							System.out.println("La quantite minimum est 1");
						} else if (qty > qtyLivre) {
							System.out.println("Le quantite maximum est " + qtyLivre);
						} else {
							String sql6 = "Insert into composer(numero_commande,quantitee,reference) values (?,?,?)";
							cmdDao.update(sql6, lastId, qty, ref);
							System.out.println("Le livre est ajoute dans votre commande.");
							Utils.readReturn();

							cmdDao.update(sql8, qtyLivre - qty, ref);
						}
					} else if (confirmation == 'N') {
						continue;
					}
				}
				break;
			case 0:
				String sql9 = "select count(reference) from composer where numero_commande =? ";
				if (cmdDao.getResult(sql9, lastId) != 0) {
					break;
				} else {
					String sql7 = "delete from commande where numero_commande=?";
					cmdDao.update(sql7, lastId);
					continuer = false;
				}
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				break;
			}
		}
	}

	public static void menu() {
		System.out.println("1- Commander un livre");
		System.out.println("0- Retour");
	}

}
