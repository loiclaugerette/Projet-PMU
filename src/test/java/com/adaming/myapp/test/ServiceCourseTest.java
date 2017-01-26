package com.adaming.myapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.abstractFactory.FactoryChevalImpl;
import com.adaming.myapp.abstractFactory.IFactoryCheval;
import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.entities.Pari;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.service.IServiceCheval;
import com.adaming.myapp.service.IServiceCourse;
import com.adaming.myapp.service.IServiceHippodrome;
import com.adaming.myapp.service.IServicePari;
import com.adaming.myapp.service.IServicePersonne;
import com.adaming.myapp.service.IServiceResultat;

public class ServiceCourseTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	
	private static IServiceCourse serviceCourse;
	private static IServiceHippodrome serviceHippodrome;
	private static IServiceCheval serviceCheval;
	private static IServicePersonne servicePersonne;
	private static IServicePari servicePari;
	private static IServiceResultat serviceResultat;
	
	private static IFactoryCheval factoryCheval;

	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceCourse = (IServiceCourse)context.getBean("ServiceCourseImpl");
		serviceHippodrome = (IServiceHippodrome)context.getBean("ServiceHippodromeImpl");
		serviceCheval = (IServiceCheval)context.getBean("ServiceChevalImpl");
		servicePersonne = (IServicePersonne)context.getBean("ServicePersonneImpl");
		servicePari = (IServicePari)context.getBean("ServicePariImpl");
		serviceResultat = (IServiceResultat)context.getBean("ServiceResultatImpl");
		factoryCheval = new FactoryChevalImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	//=========================
	// Tests
	//=========================

	@Test
	@Ignore
	public void testAdd() {
		Course course = new Course("course de noel", new Date());
		Hippodrome hippodrome = new Hippodrome("hippodromeCourse", 0.1, 0.0, 0.9, new Adresse(1, "rue de l'hippodrome", 75000, "Paris", "France"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe2", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			for (Cheval cheval:chevaux) {
				serviceCheval.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		assertNotNull(course.getIdCourse());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = serviceCourse.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Course course = serviceCourse.getOne(courses.get(0).getIdCourse());
		assertNotNull(course);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = serviceCourse.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(courses.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = serviceCourse.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Course course = serviceCourse.getOne(courses.get(0).getIdCourse());
		course.setNom("newCourseName");
		serviceCourse.update(course);
		assertThat(course.getNom(), IsEqual.equalTo(serviceCourse.getOne(courses.get(0).getIdCourse()).getNom()));
	}

	@Test
	@Ignore
	public void testGetAllRun() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = serviceCourse.getAllRun();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		System.out.println(courses);
		assertThat(courses.isEmpty(), IsEqual.equalTo(false));	}

	@Test
	@Ignore
	public void testGetAllNonRun() {
		List<Course> courses = new ArrayList<Course>();
		try {
			courses = serviceCourse.getAllNonRun();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		System.out.println(courses);
		assertThat(courses.isEmpty(), IsEqual.equalTo(false));	}

	@Test
	@Ignore
	public void testGetVainqueur() {
		Course course = new Course("course de noel", new Date());
		Hippodrome hippodrome = new Hippodrome("hippodromeCourse", 0.1, 0.0, 0.9, new Adresse(1, "rue de l'hippodrome", 75000, "Paris", "France"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe2", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			for (Cheval cheval:chevaux) {
				serviceCheval.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		Map<Double, Cheval> performances = new HashMap<Double, Cheval>();
		try {
			performances = serviceCourse.run(course.getIdCourse());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceResultat.createResultats(performances, course.getIdCourse());
		try {
			Cheval cheval = serviceCourse.getVainqueur(course.getIdCourse());
			assertNotNull(cheval.getIdCheval());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Ignore
	public void testGetChevauxByPosition() {
		Course course = new Course("course de noel", new Date());
		Hippodrome hippodrome = new Hippodrome("hippodromeCourse", 0.1, 0.0, 0.9, new Adresse(1, "rue de l'hippodrome", 75000, "Paris", "France"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe2", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			for (Cheval cheval:chevaux) {
				serviceCheval.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		Map<Double, Cheval> performances = new HashMap<Double, Cheval>();
		try {
			performances = serviceCourse.run(course.getIdCourse());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceResultat.createResultats(performances, course.getIdCourse());
		try {
			List<Cheval> classementChevaux = serviceCourse.getChevauxByPosition(course.getIdCourse());
			assertThat(6, IsEqual.equalTo(classementChevaux.size()));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Ignore
	public void testRun() {
		Course course = new Course("course de noel", new Date());
		Hippodrome hippodrome = new Hippodrome("hippodromeCourse", 0.1, 0.0, 0.9, new Adresse(1, "rue de l'hippodrome", 75000, "Paris", "France"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe2", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			for (Cheval cheval:chevaux) {
				serviceCheval.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		try {
			serviceCourse.run(course.getIdCourse());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(serviceCourse.getOne(course.getIdCourse()).getCourue(), IsEqual.equalTo(true));
	}

	@Test
	@Ignore
	public void testUpdatePariGain() {
		Course course = new Course("course de noel", new Date());
		Hippodrome hippodrome = new Hippodrome("hippodromeCourse", 0.1, 0.0, 0.9, new Adresse(1, "rue de l'hippodrome", 75000, "Paris", "France"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("Mustang", "mustangCourse2", "bai"));
			chevaux.add(factoryCheval.createCheval("PurSangArabe", "purSangArabe2", "bai"));
			chevaux.add(factoryCheval.createCheval("SelleFrancais", "selleFrancaisCourse2", "bai"));
			for (Cheval cheval:chevaux) {
				serviceCheval.add(cheval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		List<Personne> bettors = new ArrayList<Personne>();
		for (Cheval cheval:chevaux) {
			Personne personne = new Personne("PersonneCoursePariSur" + cheval.getNom(), "X", 10.0);
			bettors.add(personne);
			servicePersonne.add(personne);
			try {
				servicePari.add(new Pari(5.0), personne.getIdPersonne(), course.getIdCourse(), cheval.getIdCheval());
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
		Map<Double, Cheval> performances = new HashMap<Double, Cheval>();
		try {
			performances = serviceCourse.run(course.getIdCourse());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		serviceResultat.createResultats(performances, course.getIdCourse());
		try {
			serviceCourse.updatePariGain(course.getIdCourse());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Double newSolde = 0.0;
		for (Personne personne:bettors) {
			newSolde = newSolde + servicePersonne.getOne(personne.getIdPersonne()).getSolde();
		}
		assertThat(54.0, IsEqual.equalTo(newSolde));
	}

}
