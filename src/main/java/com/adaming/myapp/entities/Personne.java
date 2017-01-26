package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Personne implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;
	private String pseudo;
	private String motDePasse;
	private Double solde;
	
	@OneToMany(mappedBy="personne", fetch = FetchType.EAGER)
	private List<Pari> paris;
	
	//=========================
	// Constructor
	//=========================
	
	public Personne() {
	}

	public Personne(String pseudo, String motDePasse, Double solde) {
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.solde = solde;
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Pari> getParis() {
		return new ArrayList<Pari>(new HashSet<Pari>(paris));
	}

	public void setParis(List<Pari> paris) {
		this.paris = paris;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Personne [idPersonne=" + idPersonne + ", pseudo=" + pseudo
				+ ", motDePasse=" + motDePasse + ", solde=" + solde + "]";
	}
	
}
