package com.adaming.myapp.abstractFactory;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Mustang;
import com.adaming.myapp.entities.PurSangArabe;
import com.adaming.myapp.entities.SelleFrancais;
import com.adaming.myapp.exceptions.NonValidTypeException;

public class FactoryChevalImpl implements IFactoryCheval {

	@Override
	public Cheval createCheval(String typeCheval, String nom, String robe) throws NonValidTypeException {
		if (typeCheval.equalsIgnoreCase("Mustang")) {
			return new Mustang(nom, robe);
		}
		else if (typeCheval.equalsIgnoreCase("PurSangArabe")) {
			return new PurSangArabe(nom, robe);
		}
		else if (typeCheval.equalsIgnoreCase("SelleFrancais")) {
			return new SelleFrancais(nom, robe);
		}
		else {
			throw new NonValidTypeException(typeCheval + "is not a valid type for Class Cheval");
		}
	}

}
