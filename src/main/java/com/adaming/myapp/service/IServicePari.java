package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Pari;

public interface IServicePari {
	
	Pari add(final Pari pari, final Long idPersonne, final Long idCourse, final Long idCheval) throws Exception;
	
	Pari getOne(final Long idPari);
	
	List<Pari> getAll() throws Exception;
	
	Pari update(final Pari pari);

}
