package com.librairie.model.compte;

public enum TypeCompte {

	CLIENT((byte) 1), LIBRAIRE((byte) 2);

	byte numero;

	private TypeCompte(byte pInt) {
		numero = pInt;
	}

	public byte getNumero() {
		return numero;
	}

}
