package com.librairie.model.commande;

public enum StatusCommande {
	ENCOURS((byte) 1), ANNULER((byte) 2), LIVRER((byte) 3);

	byte numero;

	private StatusCommande(byte b) {
		numero = b;
	}

	public byte getNumero() {
		return numero;
	}
}
