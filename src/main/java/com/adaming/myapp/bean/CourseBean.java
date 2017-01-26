package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Resultat;
import com.adaming.myapp.service.IServiceCourse;
import com.adaming.myapp.service.IServiceResultat;

@Component("courseBean")
@ViewScoped
public class CourseBean {
	
	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("CourseBean");
	
	@Inject
	private IServiceCourse serviceCourse;
	
	@Inject
	private IServiceResultat serviceResultat;
	
	private List<Course> courses;
	private Course course;
	
	private List<Resultat> resultats;
	
	//=========================
	// Constructor
	//=========================
	
	public CourseBean() {
		initFields();
	}
	
	@PostConstruct
	public void postConstruct() {
		initLists();
	}
	
	//=========================
	// Methods
	//=========================

	public void initFields() {
		LOGGER.info("<================ CourseBean : Fields initialized ===============>");
	}

	public void initLists() {
		try {
			courses = serviceCourse.getAllRun();
		} catch (Exception e) {
			e.printStackTrace();
			courses = new ArrayList<Course>();
		}
		LOGGER.info("<================ CourseBean : Lists initialized ===============>");
	}
	
	public void getCourse(final Long idCourse) {
		course = serviceCourse.getOne(idCourse);
		try {
			resultats = serviceResultat.getResultatsByCourse(idCourse);
		} catch (Exception e) {
			e.printStackTrace();
			resultats = new ArrayList<Resultat>();
		}
		LOGGER.info("<================ CourseBean : Get Course : " + course + " ===============>");
	}

	//=========================
	// Getter / Setter
	//=========================

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Resultat> getResultats() {
		return resultats;
	}

	public void setResultats(List<Resultat> resultats) {
		this.resultats = resultats;
	}
	
}
