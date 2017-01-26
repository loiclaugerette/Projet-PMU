package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Personne;

public interface IDaoPersonne {
	
	Personne add(final Personne personne);
	
	Personne getOne(final Long idPersonne);
	
	List<Personne> getAll();
	
	Personne update(final Personne personne);

}
