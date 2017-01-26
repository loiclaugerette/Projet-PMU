package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoPari;
import com.adaming.myapp.entities.Pari;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ServicePariImpl implements IServicePari {

	//=========================
	// Attributes
	//=========================
	
	private IDaoPari dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoPari dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Pari add(Pari pari, Long idPersonne, Long idCourse, Long idCheval) throws Exception {
		return dao.add(pari, idPersonne, idCourse, idCheval);
	}

	@Override
	public Pari getOne(Long idPari) {
		return dao.getOne(idPari);
	}

	@Override
	public List<Pari> getAll() throws Exception {
		List<Pari> paris = dao.getAll();
		if (paris.size() <= 0) {
			throw new NullListException("No Pari in database");
		}
		return paris;
	}

	@Override
	public Pari update(Pari pari) {
		return dao.update(pari);
	}

}
