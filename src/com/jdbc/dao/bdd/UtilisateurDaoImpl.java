package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.IUtilisateurDao;
import com.librairie.exer.Program;
import com.librairie.model.commande.Adresse;
import com.librairie.model.compte.Compte;
import com.librairie.model.compte.TypeCompte;
import com.librairie.model.personne.Utilisateur;

public class UtilisateurDaoImpl implements IUtilisateurDao {
	Connection connection = Program.c;

	Compte compte = new Compte();
	List<Utilisateur> listUtilisateur = new ArrayList<>();
	Utilisateur utilisateur;
	Adresse adresse = new Adresse();
	ResultSet rs;

	@Override
	public void create(Utilisateur pObbject) {

		try (PreparedStatement ps = connection.prepareStatement("call AddCompteUtilisateur(?, ?, ?,?, ?, ?);");) {

			ps.setByte(1, pObbject.getCp().getType().getNumero());
			ps.setString(2, pObbject.getCp().getLogin());
			ps.setString(3, pObbject.getCp().getPassword());
			ps.setString(4, pObbject.getNom());
			ps.setString(5, pObbject.getPrenom());
			ps.setByte(6, pObbject.getAge());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Utilisateur> readAll() {

		return null;
	}

	@Override
	public void update(Utilisateur pObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur pObject) {

	}

	@Override
	public void createDemandeCompte(Utilisateur pObject) {

		try (PreparedStatement ps = connection.prepareStatement("call AjoutCompteValidation(?,?,?,?,?,?,?,?,?,?,?)");) {

			ps.setString(1, pObject.getCp().getLogin());
			ps.setString(2, pObject.getCp().getPassword());
			ps.setString(3, pObject.getNom());
			ps.setString(4, pObject.getPrenom());
			ps.setByte(5, pObject.getAge());
			ps.setInt(6, pObject.getAdresse().getNumero());
			ps.setString(7, pObject.getAdresse().getRue());
			ps.setString(8, pObject.getAdresse().getCodePostal());
			ps.setString(9, pObject.getAdresse().getVille());
			ps.setString(10, pObject.getAdresse().getPays());
			ps.setByte(11, pObject.getCp().getType().getNumero());

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<Utilisateur> getAllDemandeCompte() {

		if (!listUtilisateur.isEmpty()) {
			listUtilisateur.clear();
		}

		try (PreparedStatement ps = connection
				.prepareStatement("select * from comptevalidation natural join typecompte ;");) {

			rs = ps.executeQuery();

			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setAge(rs.getByte("age"));
				utilisateur.setPrenom(rs.getString("prenom"));
				Compte compte = new Compte();
				compte.setLogin(rs.getString("login"));
				compte.setPassword(rs.getString("password"));
				compte.setIdCompte(rs.getInt("id_compte_validation"));
				if (rs.getString("libele_type_compte").equalsIgnoreCase("Client")) {
					compte.setType(TypeCompte.CLIENT);
				} else if (rs.getString("libele_type_compte").equalsIgnoreCase("Libraire")) {
					compte.setType(TypeCompte.LIBRAIRE);
				}

				Adresse adresse = new Adresse();
				adresse.setNumero(rs.getInt("numero"));
				adresse.setPays(rs.getString("pays"));
				adresse.setRue(rs.getString("rue"));
				adresse.setVille(rs.getString("ville"));
				adresse.setCodePostal(rs.getString("cp"));
				utilisateur.setCp(compte);
				utilisateur.setAdresse(adresse);
				listUtilisateur.add(utilisateur);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listUtilisateur;

	}

	@Override
	public Utilisateur getByLogin(Utilisateur pObjet) {

		try (PreparedStatement ps = connection.prepareStatement(
				"select compteutilisateur.nom,compteutilisateur.prenom, compteutilisateur.login ,compteutilisateur.password,compteutilisateur.id_compte , typecompte.libele_type_compte \r\n"
						+ "from compteutilisateur \r\n" + "natural join typecompte \r\n" + "where login =?;");) {

			ps.setString(1, pObjet.getCp().getLogin());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				compte.setLogin(rs.getString("login"));
				compte.setPassword(rs.getString("password"));
				compte.setIdCompte(rs.getInt("id_compte"));

				if (rs.getString("libele_type_compte").equalsIgnoreCase("Client")) {
					compte.setType(TypeCompte.CLIENT);
				} else if (rs.getString("libele_type_compte").equalsIgnoreCase("Libraire")) {
					compte.setType(TypeCompte.LIBRAIRE);
				}

				pObjet.setCp(compte);
				pObjet.setNom(rs.getString("nom"));
				pObjet.setPrenom(rs.getString("prenom"));
			} else {
				pObjet = null;
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pObjet;
	}

	@Override
	public void deleteCompteValidation(Utilisateur pObjet) {
		try (PreparedStatement ps = connection
				.prepareStatement("delete from comptevalidation where id_compte_validation =?;");) {

			ps.setInt(1, pObjet.getCp().getIdCompte());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
