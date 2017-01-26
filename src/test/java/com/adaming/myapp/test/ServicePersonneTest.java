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

import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.service.IServicePersonne;

public class ServicePersonneTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	
	private static IServicePersonne servicePersonne;
	
	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		servicePersonne = (IServicePersonne)context.getBean("ServicePersonneImpl");
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
		Personne personne = new Personne("monsieur", "password", 50.0);
		servicePersonne.add(personne);
		assertNotNull(personne.getIdPersonne());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Personne> personnes = new ArrayList<Personne>();
		try {
			personnes = servicePersonne.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Personne personne = servicePersonne.getOne(personnes.get(0).getIdPersonne());
		assertNotNull(personne);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Personne> personnes = new ArrayList<Personne>();
		try {
			personnes = servicePersonne.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(personnes.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Personne> personnes = new ArrayList<Personne>();
		try {
			personnes = servicePersonne.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Personne personne = servicePersonne.getOne(personnes.get(0).getIdPersonne());
		personne.setPseudo("newPersonnePseudo");
		servicePersonne.update(personne);
		assertThat(personne.getPseudo(), IsEqual.equalTo(servicePersonne.getOne(personnes.get(0).getIdPersonne()).getPseudo()));
	}

}
