package com.adaming.myapp.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class Adresse implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	private int numero;
	private String voie;
	private int codePostal;
	private String ville;
	private String pays;
	
	//=========================
	// Constructor
	//=========================
	
	public Adresse() {
	}

	public Adresse(int numero, String voie, int codePostal, String ville,
			String pays) {
		this.numero = numero;
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	//=========================
	// Getter / Setter
	//=========================

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	//=========================
	// Methods
	//=========================
	
	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", voie=" + voie + ", codePostal="
				+ codePostal + ", ville=" + ville + ", pays=" + pays + "]";
	}
	
}
