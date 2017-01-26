package com.adaming.myapp.abstractFactory;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.exceptions.NonValidTypeException;

public interface IFactoryCheval {
	
	Cheval createCheval(final String typeCheval, final String nom, final String robe) throws NonValidTypeException;

}
