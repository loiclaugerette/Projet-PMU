package com.adaming.myapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.service.IServiceHippodrome;

public class ServiceHippodromeTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	
	private static IServiceHippodrome serviceHippodrome;
	
	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceHippodrome = (IServiceHippodrome)context.getBean("ServiceHippodromeImpl");
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
		Hippodrome hippodrome = new Hippodrome("Hydrodrome", 0.1, 0.0, 0.9, new Adresse(0, "Allée de l'hippodrome", 44120, "Vertou", "France"));
		serviceHippodrome.add(hippodrome);
		assertNotNull(hippodrome.getIdHippodrome());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Hippodrome> hippodromes = new ArrayList<Hippodrome>();
		try {
			hippodromes = serviceHippodrome.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Hippodrome hippodrome = serviceHippodrome.getOne(hippodromes.get(0).getIdHippodrome());
		assertNotNull(hippodrome);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Hippodrome> hippodromes = new ArrayList<Hippodrome>();
		try {
			hippodromes = serviceHippodrome.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(hippodromes.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Hippodrome> hippodromes = new ArrayList<Hippodrome>();
		try {
			hippodromes = serviceHippodrome.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Hippodrome hippodrome = serviceHippodrome.getOne(hippodromes.get(0).getIdHippodrome());
		hippodrome.setNom("newHippodromeName");
		serviceHippodrome.update(hippodrome);
		assertThat(hippodrome.getNom(), IsEqual.equalTo(serviceHippodrome.getOne(hippodromes.get(0).getIdHippodrome()).getNom()));
	}

}
