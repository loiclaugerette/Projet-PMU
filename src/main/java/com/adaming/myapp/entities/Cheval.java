package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type", discriminatorType = DiscriminatorType.STRING)
public abstract class Cheval implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idCheval;
	protected String nom;
	protected String robe;
	
	protected Double poids;				// 0 == heavy ; 1 == light
	protected Double coeffVitesse;
	protected Double coeffEndurance;
	protected Double coeffAgilite;
	
	@OneToMany(mappedBy="cheval", fetch = FetchType.EAGER)
	protected List<Resultat> resultats;
	
	//=========================
	// Constructor
	//=========================
	
	public Cheval() {
	}

	public Cheval(String nom, String robe) {
		this.nom = nom;
		this.robe = robe;
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdCheval() {
		return idCheval;
	}

	public void setIdCheval(Long idCheval) {
		this.idCheval = idCheval;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRobe() {
		return robe;
	}

	public void setRobe(String robe) {
		this.robe = robe;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Double getCoeffVitesse() {
		return coeffVitesse;
	}

	public void setCoeffVitesse(Double coeffVitesse) {
		this.coeffVitesse = coeffVitesse;
	}

	public Double getCoeffEndurance() {
		return coeffEndurance;
	}

	public void setCoeffEndurance(Double coeffEndurance) {
		this.coeffEndurance = coeffEndurance;
	}

	public Double getCoeffAgilite() {
		return coeffAgilite;
	}

	public void setCoeffAgilite(Double coeffAgilite) {
		this.coeffAgilite = coeffAgilite;
	}

	public List<Resultat> getResultats() {
		return new ArrayList<Resultat>(new HashSet<Resultat>(resultats));
	}

	public void setResultats(List<Resultat> resultats) {
		this.resultats = resultats;
	}
	
	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Cheval [idCheval=" + idCheval + ", nom=" + nom + ", robe="
				+ robe + ", poids=" + poids + ", coeffVitesse=" + coeffVitesse
				+ ", coeffEndurance=" + coeffEndurance + ", coeffAgilite="
				+ coeffAgilite + "]";
	}
	
	protected abstract void calcAttributes();

}
