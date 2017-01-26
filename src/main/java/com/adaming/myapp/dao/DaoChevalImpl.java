package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Cheval;

public class DaoChevalImpl implements IDaoCheval {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoChevalImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Cheval add(Cheval cheval) {
		em.persist(cheval);
		LOGGER.info("<--------------- DaoCheval : Cheval added --------------->");
		return cheval;
	}

	@Override
	public Cheval getOne(Long idCheval) {
		Cheval cheval = em.find(Cheval.class, idCheval);
		LOGGER.info("<--------------- DaoCheval : Cheval found --------------->");
		return cheval;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cheval> getAll() {
		List<Cheval> chevaux = em.createQuery("from Cheval r").getResultList();
		LOGGER.info("<--------------- DaoCheval : Chevals List recovered --------------->");
		return chevaux;
	}

	@Override
	public Cheval update(Cheval cheval) {
		em.merge(cheval);
		LOGGER.info("<--------------- DaoCheval : Cheval updated --------------->");
		return cheval;
	}

}
