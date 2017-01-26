package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Resultat;

public interface IDaoResultat {
	
	Resultat add(final Resultat resultat, final Long idCourse, final Long idCheval);
	
	Resultat getOne(final Long idResultat);
	
	List<Resultat> getAll();
	
	Resultat update(final Resultat resultat);

}