package com.jdbc.dao.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.IAdresseDao;
import com.librairie.model.commande.Adresse;

public class AdresseDaoImpl implements IAdresseDao {

	Connection connection = DatabaseConnection.getInstance().getConnection();
	PreparedStatement ps = null;

	@Override
	public void create(Adresse pObject) {
		String query = "call AddAdress(?,?,?,?,?)";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, pObject.getNumero());
			statement.setString(2, pObject.getRue());
			statement.setString(3, pObject.getVille());
			statement.setString(4, pObject.getCodePostal());
			statement.setString(5, pObject.getPays());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Adresse> readAll() {
		String query = "select ad.ID_ADRESSE, ad.NUMERO, ad.PAYS, ad.RUE, ad.VILLE, ad.CP from ADRESSE ad";
		List<Adresse> adresses = new ArrayList<>();
		
		try (Statement statement = connection.createStatement(); ResultSet result = statement.executeQuery(query)) {
			while (result.next()) {
				Adresse tempAdresse = new Adresse();
				tempAdresse.setId(result.getInt("ad.ID_ADRESSE"));
				tempAdresse.setNumero(result.getInt("ad.NUMERO"));
				tempAdresse.setRue(result.getString("ad.RUE"));
				tempAdresse.setVille(result.getString("ad.VILLE"));
				tempAdresse.setCodePostal(result.getString("ad.CP"));
				tempAdresse.setPays(result.getString("ad.PAYS"));
				adresses.add(tempAdresse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresses;
	}

	@Override
	public void update(Adresse pObject) {
	}

	@Override
	public void delete(Adresse pObject) {
	}

	@Override
	public Adresse getAdresse(Adresse pObjet) {

		try {
			ps = connection.prepareStatement("select * from adresse where adresse.cp =? and adresse.numero =? and adresse.pays =? and adresse.rue =? and adresse.ville =? ;");
			ps.setString(1, pObjet.getCodePostal());
			ps.setInt(2, pObjet.getNumero());
			ps.setString(3, pObjet.getPays());
			ps.setString(4, pObjet.getRue());
			ps.setString(5, pObjet.getVille());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pObjet.builder().codePostal(rs.getString("cp")).numero(rs.getInt("numero")).rue(rs.getString("rue"))
						.pays(rs.getString("pays")).ville(rs.getString("ville")).id(rs.getInt("id_adresse")).build();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pObjet;
	}
}
