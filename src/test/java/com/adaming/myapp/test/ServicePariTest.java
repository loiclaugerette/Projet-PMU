package com.adaming.myapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.adaming.myapp.exceptions.NonValidTypeException;
import com.adaming.myapp.service.IServiceCheval;
import com.adaming.myapp.service.IServiceCourse;
import com.adaming.myapp.service.IServiceHippodrome;
import com.adaming.myapp.service.IServicePari;
import com.adaming.myapp.service.IServicePersonne;

public class ServicePariTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	
	private static IServicePari servicePari;
	private static IServiceHippodrome serviceHippodrome;
	private static IServiceCheval serviceCheval;
	private static IServiceCourse serviceCourse;
	private static IServicePersonne servicePersonne;
	
	private static IFactoryCheval factoryCheval;

	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		servicePari = (IServicePari)context.getBean("ServicePariImpl");
		serviceHippodrome = (IServiceHippodrome)context.getBean("ServiceHippodromeImpl");
		serviceCheval = (IServiceCheval)context.getBean("ServiceChevalImpl");
		serviceCourse = (IServiceCourse)context.getBean("ServiceCourseImpl");
		servicePersonne = (IServicePersonne)context.getBean("ServicePersonneImpl");
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
		Hippodrome hippodrome = new Hippodrome("hippodromePari", 0.5, 0.5, 0.5, new Adresse(1, "rue", 12345, "ville", "pays"));
		serviceHippodrome.add(hippodrome);
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			Cheval cheval = factoryCheval.createCheval("Mustang", "chevalPari", "baiPari");
			chevaux.add(cheval);
			serviceCheval.add(cheval);
		} catch (NonValidTypeException e) {
			e.printStackTrace();
			fail();
		}
		Course course = new Course("coursePari", new Date());
		serviceCourse.add(course, hippodrome.getIdHippodrome(), chevaux);
		Personne personne = new Personne("personnePari", "passwordParii", 100.0);
		servicePersonne.add(personne);
		Pari pari = new Pari(50.0);
		try {
			servicePari.add(pari, personne.getIdPersonne(), course.getIdCourse(), chevaux.get(0).getIdCheval());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(pari.getIdPari());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Pari> paris = new ArrayList<Pari>();
		try {
			paris = servicePari.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Pari pari = servicePari.getOne(paris.get(0).getIdPari());
		assertNotNull(pari);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Pari> paris = new ArrayList<Pari>();
		try {
			paris = servicePari.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(paris.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Pari> paris = new ArrayList<Pari>();
		try {
			paris = servicePari.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Pari pari = servicePari.getOne(paris.get(0).getIdPari());
		pari.setMise(99.0);;
		servicePari.update(pari);
		assertThat(pari.getMise(), IsEqual.equalTo(servicePari.getOne(paris.get(0).getIdPari()).getMise()));
	}

}
