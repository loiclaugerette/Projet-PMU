package com.adaming.myapp.service;

import java.util.List;
import java.util.Map;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;

public interface IServiceCourse {
	
	Course add(final Course course, final Long idHippodrome, final List<Cheval> chevaux);
	
	Course getOne(final Long idCourse);
	
	List<Course> getAll() throws Exception;
	
	Course update(final Course course);
	
	List<Course> getAllRun() throws Exception;
	
	List<Course> getAllNonRun() throws Exception;
	
	Cheval getVainqueur(final Long idCourse) throws Exception;
	
	List<Cheval> getChevauxByPosition(final Long idCourse) throws Exception;
	
	Map<Double, Cheval> run(final Long idCourse) throws Exception;
	
	Course updatePariGain(final Long idCourse) throws Exception;
	
}