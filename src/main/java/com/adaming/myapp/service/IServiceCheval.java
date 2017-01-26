package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Cheval;

public interface IServiceCheval {
	
	Cheval add(final Cheval cheval);
	
	Cheval getOne(final Long idCheval);
	
	List<Cheval> getAll() throws Exception;
	
	Cheval update(final Cheval cheval);

}