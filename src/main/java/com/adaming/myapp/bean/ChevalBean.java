package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Resultat;
import com.adaming.myapp.service.IServiceCheval;

@Component("chevalBean")
@ViewScoped
public class ChevalBean {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ChevalBean");
	
	@Inject
	private IServiceCheval serviceCheval;
	
	private List<Cheval> chevaux;
	private Cheval cheval;
	private Long selectedCheval;
	
	private List<Resultat> resultats;
	
	//=========================
	// Constructor
	//=========================
	
	public ChevalBean() {
		initFields();
	}
	
	@PostConstruct
	public void postConstruct() {
		initLists();
	}

	//=========================
	// Methods
	//=========================
	
	public void initFields() {
		LOGGER.info("<================ ChevalBean : Fields initialized ===============>");
	}
	
	public void initLists() {
		try {
			chevaux = serviceCheval.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			chevaux = new ArrayList<Cheval>();
		}
		LOGGER.info("<================ ChevalBean : Lists initialized ===============>");
	}
	
	public void selectCheval() {
		cheval = serviceCheval.getOne(selectedCheval);
		resultats = cheval.getResultats();
		LOGGER.info("<================ ChevalBean : Cheval selected : " + cheval + " ===============>");
	}

	//=========================
	// Getter / Setter
	//=========================

	public List<Cheval> getChevaux() {
		return chevaux;
	}

	public void setChevaux(List<Cheval> chevaux) {
		this.chevaux = chevaux;
	}

	public Cheval getCheval() {
		return cheval;
	}

	public void setCheval(Cheval cheval) {
		this.cheval = cheval;
	}

	public Long getSelectedCheval() {
		return selectedCheval;
	}

	public void setSelectedCheval(Long selectedCheval) {
		this.selectedCheval = selectedCheval;
	}

	public List<Resultat> getResultats() {
		return resultats;
	}

	public void setResultats(List<Resultat> resultats) {
		this.resultats = resultats;
	}
	
}
