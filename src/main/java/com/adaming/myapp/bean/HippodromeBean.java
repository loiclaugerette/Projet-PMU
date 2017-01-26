package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.service.IServiceHippodrome;

@Component("hippodromeBean")
@ViewScoped
public class HippodromeBean {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("HippodromeBean");
	
	@Inject
	private IServiceHippodrome serviceHippodrome;
	
	private List<Hippodrome> hippodromes;
	private Hippodrome hippodrome;
	private Long selectedHippodrome;
	private String centerGeoMap = "47.1546318, -1.4678178000000344";
	
	//=========================
	// Constructor
	//=========================
	
	public HippodromeBean() {
		initFields();
	}
	
	@PostConstruct
	public void postConstruct() {
		initList();
	}
	
	//=========================
	// Methods
	//=========================
	
	public void initFields() {
		LOGGER.info("<=============== HippodromeBean : fields initilazed ===============>");
	}
	
	public void initList() {
		try {
			hippodromes = serviceHippodrome.getAll();
			LOGGER.info("<=============== HippodromeBean : Lists initilazed ===============>");
		} catch (Exception e) {
			e.printStackTrace();
			hippodromes = new ArrayList<Hippodrome>();
		}
	}
	
	public void selectHippodrome() {
		hippodrome = serviceHippodrome.getOne(selectedHippodrome);
		LOGGER.info("<=============== HippodromeBean : Hippodrome Selected : " + hippodrome + " ===============>");
	}

	//=========================
	// Getter / Setter
	//=========================

	public List<Hippodrome> getHippodromes() {
		return hippodromes;
	}

	public void setHippodromes(List<Hippodrome> hippodromes) {
		this.hippodromes = hippodromes;
	}

	public Hippodrome getHippodrome() {
		return hippodrome;
	}

	public void setHippodrome(Hippodrome hippodrome) {
		this.hippodrome = hippodrome;
	}

	public Long getSelectedHippodrome() {
		return selectedHippodrome;
	}

	public void setSelectedHippodrome(Long selectedHippodrome) {
		this.selectedHippodrome = selectedHippodrome;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

}
