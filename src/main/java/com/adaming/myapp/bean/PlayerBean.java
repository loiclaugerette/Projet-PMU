package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.entities.Pari;
import com.adaming.myapp.entities.Personne;
import com.adaming.myapp.service.IServiceCourse;
import com.adaming.myapp.service.IServiceHippodrome;
import com.adaming.myapp.service.IServicePari;
import com.adaming.myapp.service.IServicePersonne;

@Component("playerBean")
@ViewScoped
public class PlayerBean {
	
	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("PlayerBean");
	
	@Inject
	private AdminBean adminBean;
	
	@Inject
	private IServicePersonne servicePersonne;
	
	@Inject
	private IServiceCourse serviceCourse;
	
	@Inject
	private IServiceHippodrome serviceHippodrome;
	
	@Inject
	private IServicePari servicePari;
	
	// selected Player
	private Personne selectedPlayer;
	private String redirectPage;
	private String pseudoAdd;
	private String passwordAdd;
	private String passwordAddConfirm;
	private Double soldeAdd;
	
	// personnes
	private List<Personne> personnes;
	private String pseudoLogin;
	private String passwordLogin;
	
	// compte
	private Double soldeToAdd;
	
	// Pari
	private List<Pari> paris;
	
	// courses
	private List<Course> courses;
	private Course course;
	private List<Hippodrome> hippodromes;
	private Double mise;
	private Long chevalPari;
	
	//=========================
	// Constructor
	//=========================
	
	public PlayerBean() {
		selectedPlayer = null;
		redirectPage = "home";
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
		pseudoLogin = null;
		passwordLogin = null;
		pseudoAdd = null;
		passwordAdd = null;
		passwordAddConfirm = null;
		soldeAdd = null;
		soldeToAdd = null;
		course = null;
		LOGGER.info("<=============== PlayerBean : Fields initialized ===============>");
	}
	
	public void initLists() {
		try {
			personnes = servicePersonne.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			personnes = new ArrayList<Personne>();
		}
		try {
			courses = serviceCourse.getAllNonRun();
		} catch (Exception e) {
			e.printStackTrace();
			courses = new ArrayList<Course>();
		}
		try {
			hippodromes = serviceHippodrome.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			hippodromes = new ArrayList<Hippodrome>();
		}
		LOGGER.info("<=============== PlayerBean : Lists initialized ===============>");
	}
	
	public String login() {
		for (Personne personne:personnes) {
			if (personne.getPseudo().equals(pseudoLogin) && personne.getMotDePasse().equals(passwordLogin)) {
				selectedPlayer = personne;
				paris = selectedPlayer.getParis();
				redirectPage = "player";
				initFields();
				LOGGER.info("<=============== PlayerBean : Player Connected : " + selectedPlayer.getPseudo() + " ===============>");
				return "player?faces-redirect=true";
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Pseudo ou mot de passe incorrect"));
		redirectPage = "home";
		initFields();
		LOGGER.info("<=============== PlayerBean : Login or password incorrect ===============>");
		return "home";
	}
	
	public String addPlayer() {
		for (Personne personne:personnes) {
			if (personne.getPseudo().equals(pseudoAdd)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Pseudo déjà pris"));
				return "home";
			}
		}
		if (passwordAdd.equals(passwordAddConfirm)) {
			servicePersonne.add(new Personne(pseudoAdd, passwordAdd, soldeAdd));
			initLists();
			pseudoLogin = pseudoAdd;
			passwordLogin = passwordAdd;
			login();
			LOGGER.info("<=============== PlayerBean : Player added ===============>");
			return redirectPage + "?faces-redirect=true";
		}
		return "home";
	}
	
	public String addToSolde() {
		selectedPlayer.setSolde(selectedPlayer.getSolde() + soldeToAdd);
		servicePersonne.update(selectedPlayer);
		initFields();
		LOGGER.info("<=============== PlayerBean : Solde updated ===============>");
		return "player?faces-redirect=true";
	}
	
	public void getOneCourse(final Long idCourse) {
		course = serviceCourse.getOne(idCourse);
		LOGGER.info("<=============== PlayerBean : Course found ===============>");
	}
	
	public void parier() {
		try {
			servicePari.add(new Pari(mise), selectedPlayer.getIdPersonne(), course.getIdCourse(), chevalPari);
			selectedPlayer = servicePersonne.getOne(selectedPlayer.getIdPersonne());
			paris = selectedPlayer.getParis();
			mise = null;
			adminBean.initListCourse();
			LOGGER.info("<=============== PlayerBean : Pari saved ===============>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//=========================
	// Getter / Setter
	//=========================

	public Personne getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Personne selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	public String getRedirectPage() {
		return redirectPage;
	}

	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

	public String getPseudoAdd() {
		return pseudoAdd;
	}

	public void setPseudoAdd(String pseudoAdd) {
		this.pseudoAdd = pseudoAdd;
	}

	public String getPasswordAdd() {
		return passwordAdd;
	}

	public void setPasswordAdd(String passwordAdd) {
		this.passwordAdd = passwordAdd;
	}

	public String getPasswordAddConfirm() {
		return passwordAddConfirm;
	}

	public void setPasswordAddConfirm(String passwordAddConfirm) {
		this.passwordAddConfirm = passwordAddConfirm;
	}

	public Double getSoldeAdd() {
		return soldeAdd;
	}

	public void setSoldeAdd(Double soldeAdd) {
		this.soldeAdd = soldeAdd;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public String getPseudoLogin() {
		return pseudoLogin;
	}

	public void setPseudoLogin(String pseudoLogin) {
		this.pseudoLogin = pseudoLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	public Double getSoldeToAdd() {
		return soldeToAdd;
	}

	public void setSoldeToAdd(Double soldeToAdd) {
		this.soldeToAdd = soldeToAdd;
	}

	public List<Pari> getParis() {
		return paris;
	}

	public void setParis(List<Pari> paris) {
		this.paris = paris;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Hippodrome> getHippodromes() {
		return hippodromes;
	}

	public void setHippodromes(List<Hippodrome> hippodromes) {
		this.hippodromes = hippodromes;
	}

	public Double getMise() {
		return mise;
	}

	public void setMise(Double mise) {
		this.mise = mise;
	}

	public Long getChevalPari() {
		return chevalPari;
	}

	public void setChevalPari(Long chevalPari) {
		this.chevalPari = chevalPari;
	}

}
