package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Hippodrome;

public class DaoHippodromeImpl implements IDaoHippodrome {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoHippodromeImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Hippodrome add(Hippodrome hippodrome) {
		em.persist(hippodrome);
		LOGGER.info("<--------------- DaoHippodrome : Hippodrome added --------------->");
		return hippodrome;
	}

	@Override
	public Hippodrome getOne(Long idHippodrome) {
		Hippodrome hippodrome = em.find(Hippodrome.class, idHippodrome);
		LOGGER.info("<--------------- DaoHippodrome : Hippodrome found --------------->");
		return hippodrome;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Hippodrome> getAll() {
		List<Hippodrome> hippodromes = em.createQuery("from Hippodrome r").getResultList();
		LOGGER.info("<--------------- DaoHippodrome : Hippodromes List recovered --------------->");
		return hippodromes;
	}

	@Override
	public Hippodrome update(Hippodrome hippodrome) {
		em.merge(hippodrome);
		LOGGER.info("<--------------- DaoHippodrome : Hippodrome updated --------------->");
		return hippodrome;
	}

}
