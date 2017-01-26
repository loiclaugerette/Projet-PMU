package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Personne;

public interface IServicePersonne {
	
	Personne add(final Personne personne);
	
	Personne getOne(final Long idPersonne);
	
	List<Personne> getAll() throws Exception;
	
	Personne update(final Personne personne);

}
