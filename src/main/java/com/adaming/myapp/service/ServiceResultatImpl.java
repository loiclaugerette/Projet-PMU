package com.adaming.myapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoResultat;
import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Resultat;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ServiceResultatImpl implements IServiceResultat {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ServiceResultatImpl");
	
	private IDaoResultat dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoResultat dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Resultat add(Resultat resultat, Long idCourse, Long idCheval) {
		return dao.add(resultat, idCourse, idCheval);
	}

	@Override
	public Resultat getOne(Long idResultat) {
		return dao.getOne(idResultat);
	}

	@Override
	public List<Resultat> getAll() throws Exception {
		List<Resultat> resultats = dao.getAll();
		if (resultats.size() <= 0) {
			throw new NullListException("No Resultat in database");
		}
		return resultats;
	}

	@Override
	public Resultat update(Resultat resultat) {
		return dao.update(resultat);
	}
	
	@Override
	public List<Resultat> getResultatsByCourse(final Long idCourse) throws Exception {
		List<Resultat> resultats = dao.getAll();
		List<Resultat> resultatByCourse = new ArrayList<Resultat>();
		for (Resultat resultat:resultats) {
			if (resultat.getCourse().getIdCourse() == idCourse) {
				resultatByCourse.add(resultat);
			}
		}
		return resultatByCourse;
	}
	
	@Override
	public List<Resultat> createResultats(final Map<Double, Cheval> performances, Long idCourse) {
		Set<Double> perfoKeySet = performances.keySet();
		List<Double> perfoKeyList = new ArrayList<Double>();
		for (Double perf:perfoKeySet) {
			perfoKeyList.add(perf);
		}
		Collections.sort(perfoKeyList);
		Collections.reverse(perfoKeyList);
		List<Resultat> resultatsCourse = new ArrayList<Resultat>();
		for (int index = 1 ; index <= perfoKeyList.size() ; index++) {
			Resultat resultat = new Resultat(index);
			dao.add(resultat, idCourse, performances.get(perfoKeyList.get(index - 1)).getIdCheval());
			resultatsCourse.add(resultat);
		}
		LOGGER.info("<--------------- ServiceResultat : resultats created for course --------------->");
		return resultatsCourse;
	}

}
