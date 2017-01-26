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
import com.adaming.myapp.entities.Resultat;
import com.adaming.myapp.exceptions.NonValidTypeException;
import com.adaming.myapp.service.IServiceCheval;
import com.adaming.myapp.service.IServiceCourse;
import com.adaming.myapp.service.IServiceHippodrome;
import com.adaming.myapp.service.IServiceResultat;

public class ServiceResultatTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	private static IServiceResultat serviceResultat;
	private static IServiceHippodrome serviceHippodrome;
	private static IServiceCheval serviceCheval;
	private static IServiceCourse serviceCourse;

	private static IFactoryCheval factoryCheval;

	//=========================
	// Before / After
	//=========================
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceResultat = (IServiceResultat)context.getBean("ServiceResultatImpl");
		serviceHippodrome = (IServiceHippodrome)context.getBean("ServiceHippodromeImpl");
		serviceCheval = (IServiceCheval)context.getBean("ServiceChevalImpl");
		serviceCourse = (IServiceCourse)context.getBean("ServiceCourseImpl");
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
		Hippodrome hippodrome = new Hippodrome("hippodromeResultat", 0.5, 0.5, 0.5, new Adresse(1, "rue", 12345, "ville", "pays"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			Cheval cheval = factoryCheval.createCheval("Mustang", "chevalResultat", "baiResultat");
			chevaux.add(cheval);
			serviceCheval.add(cheval);
		} catch (NonValidTypeException e) {
			e.printStackTrace();
			fail();
		}
		Course course = new Course("coursePari", new Date());
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		Resultat resultat = new Resultat(500);
		serviceResultat.add(resultat, course.getIdCourse(), chevaux.get(0).getIdCheval());
		assertNotNull(resultat.getIdResultat());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Resultat> resultats = new ArrayList<Resultat>();
		try {
			resultats = serviceResultat.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Resultat resultat = serviceResultat.getOne(resultats.get(0).getIdResultat());
		assertNotNull(resultat);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Resultat> resultats = new ArrayList<Resultat>();
		try {
			resultats = serviceResultat.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(resultats.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Resultat> resultats = new ArrayList<Resultat>();
		try {
			resultats = serviceResultat.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Resultat resultat = serviceResultat.getOne(resultats.get(0).getIdResultat());
		resultat.setPosition(99);;
		serviceResultat.update(resultat);
		assertThat(resultat.getPosition(), IsEqual.equalTo(serviceResultat.getOne(resultats.get(0).getIdResultat()).getPosition()));
	}

	@Test
	@Ignore
	public void testCreateResultats() {
		Hippodrome hippodrome = new Hippodrome("hippodromeResultat", 0.5, 0.5, 0.5, new Adresse(1, "rue", 12345, "ville", "pays"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			Cheval cheval1 = factoryCheval.createCheval("Mustang", "chevalResultat1", "baiResultat");
			Cheval cheval2 = factoryCheval.createCheval("Mustang", "chevalResultat2", "baiResultat");
			Cheval cheval3 = factoryCheval.createCheval("Mustang", "chevalResultat3", "baiResultat");
			chevaux.add(cheval1);
			chevaux.add(cheval2);
			chevaux.add(cheval3);
			serviceCheval.add(cheval1);
			serviceCheval.add(cheval2);
			serviceCheval.add(cheval3);
		} catch (NonValidTypeException e) {
			e.printStackTrace();
			fail();
		}
		Course course = new Course("coursePari", new Date());
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		Map<Double, Cheval> performances = new HashMap<Double, Cheval>();
		performances.put(0.85, chevaux.get(0));
		performances.put(0.7, chevaux.get(1));
		performances.put(0.9, chevaux.get(2));
		List<Resultat> resultats = serviceResultat.createResultats(performances, course.getIdCourse());
		assertThat(1, IsEqual.equalTo(resultats.get(0).getPosition()));
		assertThat(2, IsEqual.equalTo(resultats.get(1).getPosition()));
		assertThat(3, IsEqual.equalTo(resultats.get(2).getPosition()));
	}

}
