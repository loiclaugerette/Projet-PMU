package com.adaming.myapp.service;

import java.util.List;
import java.util.Map;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Resultat;

public interface IServiceResultat {
	
	Resultat add(final Resultat resultat, final Long idCourse, final Long idCheval);
	
	Resultat getOne(final Long idResultat);
	
	List<Resultat> getAll() throws Exception;
	
	Resultat update(final Resultat resultat);
	
	List<Resultat> getResultatsByCourse(final Long idCourse) throws Exception;
	
	List<Resultat> createResultats(final Map<Double, Cheval> performances, final Long idCourse);

}