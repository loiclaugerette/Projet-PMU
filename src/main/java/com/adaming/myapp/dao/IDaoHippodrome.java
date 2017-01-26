package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Hippodrome;

public interface IDaoHippodrome {
	
	Hippodrome add(final Hippodrome hippodrome);
	
	Hippodrome getOne(final Long idHippodrome);
	
	List<Hippodrome> getAll();
	
	Hippodrome update(final Hippodrome hippodrome);

}
