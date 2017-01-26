package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;

public interface IDaoCourse {
	
	Course add(final Course course, final Long idHippodrome, final List<Cheval> chevaux);
	
	Course getOne(final Long idCourse);
	
	List<Course> getAll();
	
	Course update(final Course course);
		
}