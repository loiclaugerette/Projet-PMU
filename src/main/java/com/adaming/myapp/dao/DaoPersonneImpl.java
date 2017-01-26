package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Personne;

public class DaoPersonneImpl implements IDaoPersonne {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoPersonneImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Personne add(Personne personne) {
		em.persist(personne);
		LOGGER.info("<--------------- DaoPersonne : Personne added --------------->");
		return personne;
	}

	@Override
	public Personne getOne(Long idPersonne) {
		Personne personne = em.find(Personne.class, idPersonne);
		LOGGER.info("<--------------- DaoPersonne : Personne found --------------->");
		return personne;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Personne> getAll() {
		List<Personne> personnes = em.createQuery("from Personne r").getResultList();
		LOGGER.info("<--------------- DaoPersonne : Personnes List recovered --------------->");
		return personnes;
	}

	@Override
	public Personne update(Personne personne) {
		em.merge(personne);
		LOGGER.info("<--------------- DaoPersonne : Personne updated --------------->");
		return personne;
	}

}
