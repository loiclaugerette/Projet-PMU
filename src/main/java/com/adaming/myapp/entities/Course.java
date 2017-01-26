package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Course implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCourse;
	private String nom;
	private Date date;
	
	private Double totalMises;
	private Boolean courue;
	
	@ManyToOne
	@JoinColumn(name="idHippodrome")
	private Hippodrome hippodrome;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="tb_course_cheval")
	private List<Cheval> chevaux;
	
	@OneToMany(mappedBy="course")
	private List<Pari> paris;
	
	@OneToMany(mappedBy="course", fetch = FetchType.EAGER)
	private List<Resultat> classement;
	
	//=========================
	// Constructor
	//=========================
	
	public Course() {
		init();
	}

	public Course(String nom, Date date) {
		this.nom = nom;
		this.date = date;
		init();
	}
	
	//=========================
	// Getter / Setter
	//=========================

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTotalMises() {
		return totalMises;
	}

	public void setTotalMises(Double totalMises) {
		this.totalMises = totalMises;
	}

	public Boolean getCourue() {
		return courue;
	}

	public void setCourue(Boolean courue) {
		this.courue = courue;
	}

	public Hippodrome getHippodrome() {
		return hippodrome;
	}

	public void setHippodrome(Hippodrome hippodrome) {
		this.hippodrome = hippodrome;
	}

	public List<Cheval> getChevaux() {
		return new ArrayList<Cheval>(new HashSet<Cheval>(chevaux));
	}

	public void setChevaux(List<Cheval> chevaux) {
		this.chevaux = chevaux;
	}

	public List<Pari> getParis() {
		return paris;
	}

	public void setParis(List<Pari> paris) {
		this.paris = paris;
	}

	public List<Resultat> getClassement() {
		return new ArrayList<Resultat>(new HashSet<Resultat>(classement));
	}

	public void setClassement(List<Resultat> classement) {
		this.classement = classement;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Course [idCourse=" + idCourse + ", nom=" + nom + ", date="
				+ date + ", totalMises=" + totalMises + ", courue=" + courue + "]";
	}
	
	private void init() {
		courue = false;
		totalMises = 0.0;
	}
	
}
