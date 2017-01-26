package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Pari;

public interface IDaoPari {
	
	Pari add(final Pari pari, final Long idPersonne, final Long idCourse, final Long idCheval) throws Exception;
	
	Pari getOne(final Long idPari);
	
	List<Pari> getAll();
	
	Pari update(final Pari pari);

}
