package com.librairie.model.commande;

public class Commande {

	private int numeroCmd;
	private Adresse adresse;
	StatusCommande status;

	public Commande() {
	}

	public Commande(int numeroCmd, Adresse adresse, StatusCommande status) {
		super();
		this.numeroCmd = numeroCmd;
		this.adresse = adresse;
		this.status = status;
	}

	public int getNumeroCmd() {
		return numeroCmd;
	}

	public void setNumeroCmd(int numeroCmd) {
		this.numeroCmd = numeroCmd;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public StatusCommande getStatus() {
		return status;
	}

	public void setStatus(StatusCommande status) {
		this.status = status;
	}

}
