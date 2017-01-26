package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Resultat;

public class DaoResultatImpl implements IDaoResultat {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoResultatImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Resultat add(Resultat resultat, Long idCourse, Long idCheval) {
		resultat.setCheval(em.find(Cheval.class, idCheval));
		resultat.setCourse(em.find(Course.class, idCourse));
		em.persist(resultat);
		LOGGER.info("<--------------- DaoResultat : Resultat added --------------->");
		return resultat;
	}

	@Override
	public Resultat getOne(Long idResultat) {
		Resultat resultat = em.find(Resultat.class, idResultat);
		LOGGER.info("<--------------- DaoResultat : Resultat found --------------->");
		return resultat;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Resultat> getAll() {
		List<Resultat> resultats = em.createQuery("from Resultat r").getResultList();
		LOGGER.info("<--------------- DaoResultat : Resultats List recovered --------------->");
		return resultats;
	}

	@Override
	public Resultat update(Resultat resultat) {
		em.merge(resultat);
		LOGGER.info("<--------------- DaoResultat : Resultat updated --------------->");
		return resultat;
	}

}
