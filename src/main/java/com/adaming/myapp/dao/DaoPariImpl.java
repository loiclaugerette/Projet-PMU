package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Pari;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.exceptions.CourseAlreadyRunException;
import com.adaming.myapp.exceptions.NotEnoughSoldeException;

public class DaoPariImpl implements IDaoPari {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoPariImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Pari add(Pari pari, Long idPersonne, Long idCourse, Long idCheval) throws Exception {
		Course course = em.find(Course.class, idCourse);
		if (course.getCourue()) {
			throw new CourseAlreadyRunException("The course " + course + " has already been run.");
		}
		pari.setCourse(course);
		Personne personne = em.find(Personne.class, idPersonne);
		if ((personne.getSolde() - pari.getMise()) < 0.0) {
			throw new NotEnoughSoldeException(personne.getPseudo() + " has not enough money to bet.");
		}
		course.setTotalMises(course.getTotalMises() + pari.getMise());
		personne.setSolde(personne.getSolde() - pari.getMise());
		pari.setPersonne(personne);
		pari.setCheval(em.find(Cheval.class, idCheval));
		em.persist(pari);
		LOGGER.info("<--------------- DaoPari : Pari added --------------->");
		return pari;
	}

	@Override
	public Pari getOne(Long idPari) {
		Pari pari = em.find(Pari.class, idPari);
		LOGGER.info("<--------------- DaoPari : Pari found --------------->");
		return pari;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pari> getAll() {
		List<Pari> paris = em.createQuery("from Pari r").getResultList();
		LOGGER.info("<--------------- DaoPari : Paris List recovered --------------->");
		return paris;
	}

	@Override
	public Pari update(Pari pari) {
		em.merge(pari);
		LOGGER.info("<--------------- DaoPari : Pari updated --------------->");
		return pari;
	}

}
