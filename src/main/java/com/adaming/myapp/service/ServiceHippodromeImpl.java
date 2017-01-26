package com.adaming.myapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoHippodrome;
import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.exceptions.NullListException;

@Transactional
public class ServiceHippodromeImpl implements IServiceHippodrome {

	//=========================
	// Attributes
	//=========================
	
	private IDaoHippodrome dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoHippodrome dao) {
		this.dao = dao;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public Hippodrome add(Hippodrome hippodrome) {
		return dao.add(hippodrome);
	}

	@Override
	public Hippodrome getOne(Long idHippodrome) {
		return dao.getOne(idHippodrome);
	}

	@Override
	public List<Hippodrome> getAll() throws Exception {
		List<Hippodrome> hippodromes = dao.getAll();
		if (hippodromes.size() <= 0) {
			throw new NullListException("No Hippodrome in database");
		}
		return hippodromes;
	}

	@Override
	public Hippodrome update(Hippodrome hippodrome) {
		return dao.update(hippodrome);
	}

}
