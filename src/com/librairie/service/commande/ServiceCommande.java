package com.librairie.service.commande;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.jdbc.dao.bdd.CommandeDaoImpl;
import com.librairie.model.commande.Commande;
import com.librairie.model.commande.StatusCommande;
import com.librairie.model.livre.Livre;
import com.librairie.service.personne.ServiceUtilisateur;
import com.librairie.utils.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCommande {

	static CommandeDaoImpl cmdDao = new CommandeDaoImpl();
	static byte status;
	static int id;
	static int lastId;
	static List<Commande> list;

	public static void creerCmd() {

		String sql = "INSERT INTO commande(id_compte, id_status_commande,date_commande) values (?,?,?)";
		status = StatusCommande.ENCOURS.getNumero();
		id = ServiceUtilisateur.getIdCompte();
		cmdDao.update(sql, id, status, LocalDateTime.now());

		String sql1 = "SELECT LAST_INSERT_ID()";
		lastId = cmdDao.getResult(sql1);
	}

	public static void commanderLivre() {
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
				int result = 0;
				System.out.println("Entrez la reference du livre");
				int ref = Utils.readInt();

				String sql7 = "select reference from livre where reference=?";
				result = cmdDao.getResult(sql7, ref);
				if (result == 0) {
					System.out.println("Ce livre n'esite pas");
					continue;
				}

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
							String sql6 = "select quantitee from composer where reference =? and numero_commande =?";
							int qtyOld = cmdDao.getResult(sql6, ref, lastId);

							String sql4 = "update composer set quantitee =? where numero_commande = ?";
							cmdDao.update(sql4, newQty, lastId);
							System.out.println("La quantite est modifiee");

							cmdDao.update(sql8, qtyLivre - newQty + qtyOld, ref);
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
						continuer = supprimerCmd();
					}
				}
				break;
			case 0:
				continuer = supprimerCmd();
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				break;
			}
		}
	}

	public static boolean supprimerCmd() {
		String sql = "select count(reference) from composer where numero_commande =? ";
		if (cmdDao.getResult(sql, lastId) != 0) {
			return false;
		} else {
			String sql1 = "delete from commande where numero_commande=?";
			cmdDao.update(sql1, lastId);
			return false;
		}
	}

	public static void menu() {
		System.out.println("1- Commander un livre");
		System.out.println("0- Retour");
	}

	public static void listerCmdLibraire() {
		String sql = "select commande.numero_commande ,commande.date_commande,sum((composer.quantitee * livre.prix)) as prix_total, compteutilisateur.nom ,compteutilisateur.prenom ,statuscommande.libele_status_commande \n"
				+ "from composer\n" + "natural join  commande\n"
				+ "inner join livre on livre.reference =composer.reference\n"
				+ "inner join compteutilisateur on compteutilisateur.id_compte =commande.id_compte\n"
				+ "inner join statuscommande on statuscommande .id_status_commande  =commande .id_status_commande \n"
				+ "group by numero_commande;";
		list = cmdDao.readAll(sql);

		for (Commande c : list) {
			System.out.println(c);
		}
		Utils.readReturn();
	}

	public static void listerCmdClient() {
		String sql = "select commande.numero_commande, commande.date_commande, sum((composer.quantitee * livre.prix)) as prix_total, compteutilisateur.nom ,compteutilisateur.prenom ,statuscommande.libele_status_commande \n"
				+ "from composer\n" + "natural join  commande\n"
				+ "inner join livre on livre.reference =composer.reference\n"
				+ "inner join statuscommande on statuscommande .id_status_commande  =commande .id_status_commande \n"
				+ "inner join compteutilisateur on compteutilisateur.id_compte =commande.id_compte\n"
				+ "where commande.id_compte=?\n" + "group by numero_commande;";

		list = cmdDao.readAll(sql, ServiceUtilisateur.getIdCompte());

		for (Commande c : list) {
			System.out.println(c);
		}
		Utils.readReturn();
	}

	public static void modifierEtatCmd() {
		System.out.println("Entrez le numero de commande");
		int nb = Utils.readInt();

		String sql = "select id_status_commande from commande where numero_commande=?";
		int status = cmdDao.getResult(sql, nb);

		String sql1 = "update commande set id_status_commande =? where numero_commande =?";

		if (status == 0) {
			System.out.println("Cette commande n'exsite pas");
		} else if (status == 1) {
			System.out.println("Modifiez la commande en \n1- Livrer \n0- Retour");
			int choix = Utils.readInt();
			switch (choix) {
			case 1:
				cmdDao.update(sql1, 2, nb);
				System.out.println("Votre action est reussie");
				Utils.readReturn();
				break;
			case 0:
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				break;
			}
		} else if (status == 2 || status == 3) {
			System.out.println("Vous ne pouvez pas modifier l'etat de commande.");
		}
	}

	public static void annulerCmd() {
		System.out.println("Entrez le numero de commande");
		int nb = Utils.readInt();

		String sql = "select id_status_commande from commande where numero_commande=? and id_compte =?";
		int status = cmdDao.getResult(sql, nb, ServiceUtilisateur.getIdCompte());

		String sql1 = "update commande set id_status_commande =? where numero_commande =?";

		if (status == 0) {
			System.out.println("Cette commande n'exsite pas");
		} else if (status == 1) {
			System.out.println("Modifiez la commande en \n1- Annuler \n0- Retour");
			int choix = Utils.readInt();
			switch (choix) {
			case 1:
				cmdDao.update(sql1, 3, nb);
				System.out.println("Votre action est reussie");

				String sql2 = "select quantitee,reference from composer where numero_commande =?";
				HashMap<Integer, Integer> map = cmdDao.contenuCmd(sql2, nb);

				for (Integer key : map.keySet()) {
					Integer value = map.get(key);
					String sql3 = "select quantitee from livre where reference =?";
					int qty = cmdDao.getResult(sql3, key);
					String sql8 = "update livre set quantitee =? where reference =?";
					cmdDao.update(sql8, qty + value, key);

				}

				Utils.readReturn();
				break;
			case 0:
				break;
			default:
				System.out.println("Votre saisie n'est pas correcte.");
				break;
			}
		} else if (status == 2 || status == 3) {
			System.out.println("Vous ne pouvez pas modifier l'etat de commande.");
		}
	}

}
