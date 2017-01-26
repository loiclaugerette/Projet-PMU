package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Hippodrome;

public class DaoCourseImpl implements IDaoCourse {

	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoCourseImpl");

	//=========================
	// Methods
	//=========================

	@Override
	public Course add(Course course, Long idHippodrome, List<Cheval> chevaux) {
		course.setHippodrome(em.find(Hippodrome.class, idHippodrome));
		course.setChevaux(chevaux);
		em.persist(course);
		LOGGER.info("<--------------- DaoCourse : Course added --------------->");
		return course;
	}

	@Override
	public Course getOne(Long idCourse) {
		Course course = em.find(Course.class, idCourse);
		LOGGER.info("<--------------- DaoCourse : Course found --------------->");
		return course;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Course> getAll() {
		List<Course> courses = em.createQuery("from Course r").getResultList();
		LOGGER.info("<--------------- DaoCourse : Courses List recovered --------------->");
		return courses;
	}

	@Override
	public Course update(Course course) {
		em.merge(course);
		LOGGER.info("<--------------- DaoCourse : Course updated --------------->");
		return course;
	}

}
