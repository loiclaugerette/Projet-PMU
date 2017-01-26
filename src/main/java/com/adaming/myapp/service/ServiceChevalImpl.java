package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoCheval;
import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ServiceChevalImpl implements IServiceCheval {

	//=========================
	// Attributes
	//=========================
	
	private IDaoCheval dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoCheval dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Cheval add(Cheval cheval) {
		return dao.add(cheval);
	}

	@Override
	public Cheval getOne(Long idCheval) {
		return dao.getOne(idCheval);
	}

	@Override
	public List<Cheval> getAll() throws Exception {
		List<Cheval> chevaux = dao.getAll();
		if (chevaux.size() <= 0) {
			throw new NullListException("No Cheval in database");
		}
		return chevaux;
	}

	@Override
	public Cheval update(Cheval cheval) {
		return dao.update(cheval);
	}

}