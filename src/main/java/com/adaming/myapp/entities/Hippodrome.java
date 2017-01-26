package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Hippodrome implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHippodrome;
	private String nom;
	private Double longueurPiste;
	private Double obstacles;
	private Double natureSol;		// 0 == hard ; 1 == soft
	
	@Embedded
	private Adresse adresse;
	
	@OneToMany(mappedBy="hippodrome")
	private List<Course> courses;
	
	//=========================
	// Constructor
	//=========================
	
	public Hippodrome() {
		verifyAttributeValue();
	}

	public Hippodrome(String nom, Double longueurPiste, Double obstacles, Double natureSol, Adresse adresse) {
		this.nom = nom;
		this.longueurPiste = longueurPiste;
		this.obstacles = obstacles;
		this.natureSol = natureSol;
		this.adresse = adresse;
		verifyAttributeValue();
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdHippodrome() {
		return idHippodrome;
	}

	public void setIdHippodrome(Long idHippodrome) {
		this.idHippodrome = idHippodrome;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getLongueurPiste() {
		return longueurPiste;
	}

	public void setLongueurPiste(Double longueurPiste) {
		this.longueurPiste = longueurPiste;
		verifyAttributeValue();
	}

	public Double getObstacles() {
		return obstacles;
	}

	public void setObstacles(Double obstacles) {
		this.obstacles = obstacles;
		verifyAttributeValue();
	}

	public Double getNatureSol() {
		return natureSol;
	}

	public void setNatureSol(Double natureSol) {
		this.natureSol = natureSol;
		verifyAttributeValue();
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	//=========================
	// Methods
	//=========================
	
	@Override
	public String toString() {
		return "Hippodrome [idHippodrome=" + idHippodrome + ", nom=" + nom
				+ ", longueurPiste=" + longueurPiste + ", natureSol="
				+ natureSol + "]";
	}
	
	public void setParameters(Double longueurPiste, Double obstacles, Double natureSol) {
		this.longueurPiste = longueurPiste;
		this.obstacles = obstacles;
		this.natureSol = natureSol;
		verifyAttributeValue();
	}

	private void verifyAttributeValue() {
		if (longueurPiste != null && obstacles != null && natureSol != null) {
			Double totalAttribute = longueurPiste + obstacles + natureSol;
			if (totalAttribute == 0.0) {
				longueurPiste = 1.0 / 3;
				obstacles = 1.0 / 3;
				natureSol = 1.0 / 3;
			}
			else {
				longueurPiste = longueurPiste / totalAttribute;
				obstacles = obstacles / totalAttribute;
				natureSol = natureSol / totalAttribute;
			}
		}
		else {
			longueurPiste = 1.0 / 3;
			obstacles = 1.0 / 3;
			natureSol = 1.0 / 3;
		}
	}
	
}
