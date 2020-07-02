package com.librairie.model.compte;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {

	TypeCompte type;
	private String login;
	private String password;
	private int idCompte;

}
