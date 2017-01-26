package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Cheval;

public interface IDaoCheval {
	
	Cheval add(final Cheval cheval);
	
	Cheval getOne(final Long idCheval);
	
	List<Cheval> getAll();
	
	Cheval update(final Cheval cheval);

}