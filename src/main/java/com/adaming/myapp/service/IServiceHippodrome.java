package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Hippodrome;

public interface IServiceHippodrome {
	
	Hippodrome add(final Hippodrome hippodrome);
	
	Hippodrome getOne(final Long idHippodrome);
	
	List<Hippodrome> getAll() throws Exception;
	
	Hippodrome update(final Hippodrome hippodrome);

}
