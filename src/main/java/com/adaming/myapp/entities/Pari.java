package com.adaming.myapp.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Pari implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPari;
	private Double mise;
	private Double gain;
	
	@ManyToOne
	@JoinColumn(name="idPersonne")
	private Personne personne;
	
	@ManyToOne
	@JoinColumn(name="idCourse")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="idCheval")
	private Cheval cheval;
	
	//=========================
	// Constructor
	//=========================
	
	public Pari() {
	}

	public Pari(Double mise) {
		this.mise = mise;
	}
	
	//=========================
	// Getter / Setter
	//=========================

	public Long getIdPari() {
		return idPari;
	}

	public void setIdPari(Long idPari) {
		this.idPari = idPari;
	}

	public Double getMise() {
		return mise;
	}

	public void setMise(Double mise) {
		this.mise = mise;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Cheval getCheval() {
		return cheval;
	}

	public void setCheval(Cheval cheval) {
		this.cheval = cheval;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Pari [idPari=" + idPari + ", mise=" + mise + ", gain=" + gain
				+ "]";
	}

}