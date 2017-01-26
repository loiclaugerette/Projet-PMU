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

import com.adaming.myapp.abstractFactory.FactoryChevalImpl;
import com.adaming.myapp.abstractFactory.IFactoryCheval;
import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.exceptions.NonValidTypeException;
import com.adaming.myapp.service.IServiceCheval;

public class ServiceChevalTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;
	
	private static IServiceCheval serviceCheval;
	
	private static IFactoryCheval factoryCheval;

	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceCheval = (IServiceCheval)context.getBean("ServiceChevalImpl");
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
		try {
			Cheval mustang = factoryCheval.createCheval("Mustang", "mustang", "bai");
			Cheval purSangArabe = factoryCheval.createCheval("PurSangArabe", "pur Sang Arabe", "noir");
			Cheval selleFrancais = factoryCheval.createCheval("SelleFrancais", "selle Francais", "bai-brun");
			serviceCheval.add(mustang);
			serviceCheval.add(purSangArabe);
			serviceCheval.add(selleFrancais);
			assertNotNull(mustang.getIdCheval());
			assertNotNull(purSangArabe.getIdCheval());
			assertNotNull(selleFrancais.getIdCheval());
		} catch (NonValidTypeException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux = serviceCheval.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Cheval cheval = serviceCheval.getOne(chevaux.get(0).getIdCheval());
		assertNotNull(cheval);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux = serviceCheval.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(chevaux.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Cheval> chevaux = new ArrayList<Cheval>();
		try {
			chevaux = serviceCheval.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Cheval cheval = serviceCheval.getOne(chevaux.get(0).getIdCheval());
		cheval.setNom("newChevalName");
		serviceCheval.update(cheval);
		assertThat(cheval.getNom(), IsEqual.equalTo(serviceCheval.getOne(chevaux.get(0).getIdCheval()).getNom()));
	}

}
