package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoPersonne;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ServicePersonneImpl implements IServicePersonne {

	//=========================
	// Attributes
	//=========================
	
	private IDaoPersonne dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoPersonne dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Personne add(Personne personne) {
		return dao.add(personne);
	}

	@Override
	public Personne getOne(Long idPersonne) {
		return dao.getOne(idPersonne);
	}

	@Override
	public List<Personne> getAll() throws Exception {
		List<Personne> personnes = dao.getAll();
		if (personnes.size() <= 0) {
			throw new NullListException("No Personne in database");
		}
		return personnes;
	}

	@Override
	public Personne update(Personne personne) {
		return dao.update(personne);
	}

}
