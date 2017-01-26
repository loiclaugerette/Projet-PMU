package com.adaming.myapp.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("Mustang")
public class Mustang extends Cheval implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Transient
	private final static Double COEFF_POIDS = 0.2;
	@Transient
	private final static Double COEFF_VITESSE = 0.3;
	@Transient
	private final static Double COEFF_ENDURANCE = 0.25;
	@Transient
	private final static Double COEFF_AGILITE = 0.25;

	//=========================
	// Constructor
	//=========================
	
	public Mustang() {
		super();
		calcAttributes();
	}

	public Mustang(String nom, String robe) {
		super(nom, robe);
		calcAttributes();
	}
	
	//=========================
	// Getter / Setter
	//=========================

	//=========================
	// Methods
	//=========================
	
	@Override
	public String toString() {
		return "Mustang [idCheval=" + idCheval + ", nom=" + nom + ", robe="
				+ robe + ", poids=" + poids + ", coeffVitesse=" + coeffVitesse
				+ ", coeffEndurance=" + coeffEndurance + ", coeffAgilite="
				+ coeffAgilite + "]";
	}
	
	@Override
	protected void calcAttributes() {
		poids = (Math.random() * 0.1 - 0.05) + COEFF_POIDS;
		coeffVitesse = (Math.random() * 0.1 - 0.05) + COEFF_VITESSE;
		coeffEndurance = (Math.random() * 0.1 - 0.05) + COEFF_ENDURANCE;
		coeffAgilite = (Math.random() * 0.1 - 0.05) + COEFF_AGILITE;
	}

}
