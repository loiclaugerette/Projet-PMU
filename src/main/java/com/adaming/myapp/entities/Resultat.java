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
public class Resultat implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResultat;
	private int position;
	
	@ManyToOne
	@JoinColumn(name="idCourse")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="idCheval")
	private Cheval cheval;
	
	//=========================
	// Constructor
	//=========================
	
	public Resultat() {
	}

	public Resultat(int position) {
		this.position = position;
	}

	//=========================
	// Getter / Setter
	//=========================


	public Long getIdResultat() {
		return idResultat;
	}

	public void setIdResultat(Long idResultat) {
		this.idResultat = idResultat;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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
		return "Resultat [idResultat=" + idResultat + ", position=" + position
				+ "]";
	}
	
}
