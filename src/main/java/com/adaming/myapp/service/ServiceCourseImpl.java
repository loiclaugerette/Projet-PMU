package com.adaming.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoCourse;
import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.entities.Pari;
import com.adaming.myapp.entities.Resultat;
import com.adaming.myapp.exceptions.CourseAlreadyRunException;
import com.adaming.myapp.exceptions.CourseNotRunException;
import com.adaming.myapp.exceptions.NoWinnerException;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ServiceCourseImpl implements IServiceCourse {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ServiceCourseImpl");
	
	private IDaoCourse dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoCourse dao) {
		this.dao = dao;
	}
	
	//=========================
	// Methods
	//=========================

	@Override
	public Course add(Course course, Long idHippodrome, List<Cheval> chevaux) {
		return dao.add(course, idHippodrome, chevaux);
	}

	@Override
	public Course getOne(Long idCourse) {
		return dao.getOne(idCourse);
	}

	@Override
	public List<Course> getAll() throws Exception {
		List<Course> courses = dao.getAll();
		if (courses.size() <= 0) {
			throw new NullListException("No Course in database");
		}
		return courses;
	}

	@Override
	public Course update(Course course) {
		return dao.update(course);
	}
	
	@Override
	public List<Course> getAllRun() throws Exception {
		List<Course> courses = getAll();
		List<Course> coursesRun = new ArrayList<Course>();
		for (Course course:courses) {
			if (course.getCourue()) {
				coursesRun.add(course);
			}
		}
		LOGGER.info("<--------------- ServiceCourse : List of run Courses recovered --------------->");
		return coursesRun;
	}

	@Override
	public List<Course> getAllNonRun() throws Exception {
		List<Course> courses = getAll();
		List<Course> coursesNonRun = new ArrayList<Course>();
		for (Course course:courses) {
			if (!course.getCourue()) {
				coursesNonRun.add(course);
			}
		}
		LOGGER.info("<--------------- ServiceCourse : List of non run Courses recovered --------------->");
		return coursesNonRun;
	}

	
	@Override
	public Cheval getVainqueur(Long idCourse) throws Exception {
		Course course = dao.getOne(idCourse);
		if (!course.getCourue()) {
			throw new CourseNotRunException("The Course " + course + " must be run before recovering winner.");
		}
		Cheval vainqueur = null;
		for (Resultat resultat:course.getClassement()) {
			if (resultat.getPosition() == 1) {
				vainqueur = resultat.getCheval();
			}
		}
		if (vainqueur == null) {
			throw new NoWinnerException("No winner for the course " + course);
		}
		LOGGER.info("<--------------- ServiceCourse : Course winner found --------------->");
		return vainqueur;
	}

	@Override
	public List<Cheval> getChevauxByPosition(Long idCourse) throws Exception {
		Course course = dao.getOne(idCourse);
		if (!course.getCourue()) {
			throw new CourseNotRunException("The Course " + course + " must be run before recovering winner.");
		}
		List<Cheval> chevaux = new ArrayList<Cheval>();
		List<Resultat> resultats = course.getClassement();
		for (int index = 1 ; index <= resultats.size() ; index++) {
			for(Resultat resultat:resultats) {
				if (resultat.getPosition() == index) {
					chevaux.add(resultat.getCheval());
				}
			}
		}
		LOGGER.info("<--------------- ServiceCourse : Ordered List of Cheval for Course recovered --------------->");
		return chevaux;
	}

	@Override
	public Map<Double, Cheval> run(Long idCourse) throws Exception {
		Course course = dao.getOne(idCourse);
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + idCourse + " " + course);
		if (course.getCourue()) {
			throw new CourseAlreadyRunException("The course " + course + " has already been run.");
		}
		List<Cheval> chevaux = course.getChevaux();
		Map<Double, Cheval> performances = new HashMap<Double, Cheval>();
		for (Cheval cheval:chevaux) {
			performances.put(calcPerformance(cheval, course.getHippodrome()), cheval);
		}
		course.setCourue(true);
		dao.update(course);
		LOGGER.info("<--------------- ServiceCourse : Course run --------------->");
		return performances;
	}
	
	@Override
	public Course updatePariGain(Long idCourse) throws Exception {
		Course course = dao.getOne(idCourse);
		Cheval vaiqueur = getVainqueur(idCourse);
		List<Pari> paris = course.getParis();
		Double totalMiseVainqueurs = 0.0;
		for (Pari pari:paris) {
			if (pari.getCheval().getIdCheval() == vaiqueur.getIdCheval()) {
				totalMiseVainqueurs = totalMiseVainqueurs + pari.getMise();
			}
		}
		for (Pari pari:paris) {
			if (pari.getCheval().getIdCheval() == vaiqueur.getIdCheval()) {
				Double gain = (pari.getMise() / totalMiseVainqueurs) * (0.8 * course.getTotalMises());
				pari.setGain(gain);
				pari.getPersonne().setSolde(pari.getPersonne().getSolde() + gain);
			}
		}
		dao.update(course);
		LOGGER.info("<--------------- ServiceCourse : Gain for Course updated --------------->");
		return course;
	}

	private Double calcPerformance(Cheval cheval, Hippodrome hippodrome) {
		return ((Math.random() * 0.1 - 0.05) * 
				(cheval.getCoeffVitesse() + 
						cheval.getPoids() * hippodrome.getNatureSol() + 
						cheval.getCoeffEndurance() * hippodrome.getLongueurPiste() + 
						cheval.getCoeffAgilite() * hippodrome.getObstacles()));
	}
	
}
