package com.adaming.myapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;
import org.springframework.stereotype.Component;

import com.adaming.myapp.abstractFactory.FactoryChevalImpl;
import com.adaming.myapp.abstractFactory.IFactoryCheval;
import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.Cheval;
import com.adaming.myapp.entities.Course;
import com.adaming.myapp.entities.Hippodrome;
import com.adaming.myapp.entities.Resultat;
import com.adaming.myapp.exceptions.NonValidTypeException;
import com.adaming.myapp.service.IServiceCheval;
import com.adaming.myapp.service.IServiceCourse;
import com.adaming.myapp.service.IServiceHippodrome;
import com.adaming.myapp.service.IServiceResultat;

@Component("adminBean")
@ViewScoped
public class AdminBean {
	
	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("AdminBean");
	
	@Inject
	private HippodromeBean hippodromeBean;
	
	@Inject
	private ChevalBean chevalBean;
	
	@Inject
	private CourseBean courseBean;
	
	@Inject
	private PlayerBean playerBean;
	
	@Inject
	private IServiceHippodrome serviceHippodrome;
	
	@Inject
	private IServiceCheval serviceCheval;
	private IFactoryCheval factoryCheval;
	
	@Inject
	private IServiceCourse serviceCourse;
	
	@Inject
	private IServiceResultat serviceResultat;
		
	// login
	private String password;
	private String redirectPage;
	
	// Hippodrome
	private List<Hippodrome> hippodromes;
	private Hippodrome hippodrome;
	private String hippodromeNom;
	private Integer hippodromeLongueurPiste;
	private Integer hippodromeObstacles;
	private Integer hippodromeNatureSol;
	private int hippodromeNumero;
	private String hippodromeVoie;
	private int hippodromeCodePostal;
	private String hippodromeVille;
	private String hippodromePays;
	
	// Cheval
	private List<Cheval> chevaux;
	private Cheval cheval;
	private String chevalNom;
	private String chevalRace;
	private String chevalRobe;
	
	// Course
	private List<Course> courses;
	private Course course;
	private String courseNom;
	private Date courseDate;
	private Long courseHippodrome;
	private DualListModel<Cheval> courseChevaux;
	private Long idCourseToRun;
	
	// Resultat
	private List<Resultat> resultats;
	
	//=========================
	// Constructor
	//=========================
	
	public AdminBean() {
		redirectPage = "home";
		factoryCheval = new FactoryChevalImpl();
		initFields();
	}
	
	@PostConstruct
	public void postConstruct() {
		initLists();
	}
	
	//=========================
	// Methods
	//=========================
	
	//-------------------------
	// Initialization
	//-------------------------

	public void initFields() {
		password = null;
		initFieldsHippodrome();
		initFieldsCheval();
		initFieldsCourse();
		LOGGER.info("<=============== AdminBean : Fields initialized ===============>");
	}
	
	public void initFieldsHippodrome() {
		hippodrome = null;
		hippodromeNom = null;
		hippodromeLongueurPiste = 0;
		hippodromeObstacles = 0;
		hippodromeNatureSol = 0;
		hippodromeNumero = 0;
		hippodromeVoie = null;
		hippodromeCodePostal = 0;
		hippodromeVille = null;
		hippodromePays = null;
		LOGGER.info("<=============== AdminBean : Hippodrome Fields initialized ===============>");
	}
	
	public void initFieldsCheval() {
		cheval = null;
		chevalNom = null;
		chevalRace = null;
		chevalRobe = null;
		LOGGER.info("<=============== AdminBean : Cheval Fields initialized ===============>");
	}
	
	public void initFieldsCourse() {
		course = null;
		courseNom = null;
		courseDate = null;
		courseHippodrome = 0L;
		idCourseToRun = 0L;
		LOGGER.info("<=============== AdminBean : Course Fields initialized ===============>");
	}
	
	public void initLists() {
		initListHippodrome();
		initListCheval();
		initListCourse();
		LOGGER.info("<=============== AdminBean : Lists initialized ===============>");
	}
	
	public void initListHippodrome() {
		try {
			hippodromes = serviceHippodrome.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			hippodromes = new ArrayList<Hippodrome>();
		}
		LOGGER.info("<=============== AdminBean : Hippodrome List initialized ===============>");
	}
	
	public void initListCheval() {
		try {
			chevaux = serviceCheval.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			chevaux = new ArrayList<Cheval>();
		}
		LOGGER.info("<=============== AdminBean : Cheval List initialized ===============>");
	}
	
	public void initListCourse() {
		try {
			courses = serviceCourse.getAllNonRun();
		} catch (Exception e) {
			e.printStackTrace();
			courses = new ArrayList<Course>();
		}
		try {
			courseChevaux = new DualListModel<Cheval>(serviceCheval.getAll(), new ArrayList<Cheval>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("<=============== AdminBean : Course List initialized ===============>");
	}
	
	//-------------------------
	// Login
	//-------------------------

	public String login() {
		if (password.equals("intipmu")) {
			redirectPage = "admin";
			LOGGER.info("<=============== AdminBean : Administrator Logged ===============>");
			initFields();
			initLists();
			return "admin?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Mot de passe incorrect"));
			redirectPage = "home.xhtml";
			LOGGER.info("<=============== AdminBean : Password refused ===============>");
			initFields();
			return "home";
		}
	}
	
	//-------------------------
	// Hippodrome
	//-------------------------

	public String addHippodrome() {
		serviceHippodrome.add(new Hippodrome(hippodromeNom, 1.0*hippodromeLongueurPiste, 1.0*hippodromeObstacles, 1.0*(10-hippodromeNatureSol), 
				new Adresse(hippodromeNumero, hippodromeVoie, hippodromeCodePostal, hippodromeVille, hippodromePays)));
		initFieldsHippodrome();
		initListHippodrome();
		hippodromeBean.initList();
		playerBean.initLists();
		LOGGER.info("<=============== AdminBean : Hippodrome added ===============>");
		return "admin?faces-redirect=true";
	}
	
	public void getOneHippodrome(final Long idHippodrome) {
		hippodrome = serviceHippodrome.getOne(idHippodrome);
		hippodromeLongueurPiste = (int)(hippodrome.getLongueurPiste() * 10);
		hippodromeObstacles = (int)(hippodrome.getObstacles() * 10);
		hippodromeNatureSol = (int)(10 - hippodrome.getNatureSol() * 10);
		LOGGER.info("<=============== AdminBean : Hippodrome found " + hippodrome + " ===============>");
	}
	
	public void updateHippodrome() {
		hippodrome.setParameters(1.0*hippodromeLongueurPiste, 1.0*hippodromeObstacles, 1.0*hippodromeNatureSol);
		serviceHippodrome.update(hippodrome);
		initFieldsHippodrome();
		initListHippodrome();
		LOGGER.info("<=============== AdminBean : Hippodrome updated ===============>");
	}

	//-------------------------
	// Cheval
	//-------------------------
	
	public String addCheval() {
		try {
			serviceCheval.add(factoryCheval.createCheval(chevalRace, chevalNom, chevalRobe));
			initFieldsCheval();
			initListCheval();
			chevalBean.initLists();
			LOGGER.info("<=============== AdminBean : Cheval added ===============>");
		} catch (NonValidTypeException e) {
			e.printStackTrace();
		}
		return "admin?faces-redirect=true";
	}
	
	public void getOneCheval(final Long idCheval) {
		cheval = serviceCheval.getOne(idCheval);
		LOGGER.info("<=============== AdminBean : Cheval found " + cheval + " ===============>");
	}
	
	public void updateCheval() {
		serviceCheval.update(cheval);
		initFieldsCheval();
		initListCheval();
		LOGGER.info("<=============== AdminBean : Cheval updated ===============>");
	}

	//-------------------------
	// Course
	//-------------------------
	
	public String addCourse() {
		serviceCourse.add(new Course(courseNom, courseDate), courseHippodrome, courseChevaux.getTarget());
		initFieldsCourse();
		initListCourse();
		courseBean.initLists();
		playerBean.initLists();
		LOGGER.info("<=============== AdminBean : Course added ===============>");
		return "admin?faces-redirect=true";
	}
	
	public void getOneCourse(final Long idCourse) {
		course = serviceCourse.getOne(idCourse);
		LOGGER.info("<=============== AdminBean : Course found " + course + " ===============>");
	}
	
	public void updateCourse() {
		serviceCourse.update(course);
		initFieldsCourse();
		initListCourse();
		LOGGER.info("<=============== AdminBean : Course updated ===============>");
	}

	//-------------------------
	// Courir
	//-------------------------
	
	public String runCourse() {
		try {
			Map<Double, Cheval> performances = serviceCourse.run(idCourseToRun);
			serviceResultat.createResultats(performances, idCourseToRun);
			serviceCourse.updatePariGain(idCourseToRun);
			resultats = serviceCourse.getOne(idCourseToRun).getClassement();
			initLists();
			LOGGER.info("<=============== AdminBean : Course run ===============>");
			return "admin?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin?faces-redirect=true";
	}

	//=========================
	// Getter / Setter
	//=========================

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedirectPage() {
		return redirectPage;
	}

	public void setRedirectPage(String redirectPage) {
		this.redirectPage = redirectPage;
	}

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

	public String getHippodromeNom() {
		return hippodromeNom;
	}

	public void setHippodromeNom(String hippodromeNom) {
		this.hippodromeNom = hippodromeNom;
	}

	public Integer getHippodromeLongueurPiste() {
		return hippodromeLongueurPiste;
	}

	public void setHippodromeLongueurPiste(Integer hippodromeLongueurPiste) {
		this.hippodromeLongueurPiste = hippodromeLongueurPiste;
	}

	public Integer getHippodromeObstacles() {
		return hippodromeObstacles;
	}

	public void setHippodromeObstacles(Integer hippodromeObstacles) {
		this.hippodromeObstacles = hippodromeObstacles;
	}

	public Integer getHippodromeNatureSol() {
		return hippodromeNatureSol;
	}

	public void setHippodromeNatureSol(Integer hippodromeNatureSol) {
		this.hippodromeNatureSol = hippodromeNatureSol;
	}

	public int getHippodromeNumero() {
		return hippodromeNumero;
	}

	public void setHippodromeNumero(int hippodromeNumero) {
		this.hippodromeNumero = hippodromeNumero;
	}

	public String getHippodromeVoie() {
		return hippodromeVoie;
	}

	public void setHippodromeVoie(String hippodromeVoie) {
		this.hippodromeVoie = hippodromeVoie;
	}

	public int getHippodromeCodePostal() {
		return hippodromeCodePostal;
	}

	public void setHippodromeCodePostal(int hippodromeCodePostal) {
		this.hippodromeCodePostal = hippodromeCodePostal;
	}

	public String getHippodromeVille() {
		return hippodromeVille;
	}

	public void setHippodromeVille(String hippodromeVille) {
		this.hippodromeVille = hippodromeVille;
	}

	public String getHippodromePays() {
		return hippodromePays;
	}

	public void setHippodromePays(String hippodromePays) {
		this.hippodromePays = hippodromePays;
	}

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

	public String getChevalNom() {
		return chevalNom;
	}

	public void setChevalNom(String chevalNom) {
		this.chevalNom = chevalNom;
	}

	public String getChevalRace() {
		return chevalRace;
	}

	public void setChevalRace(String chevalRace) {
		this.chevalRace = chevalRace;
	}

	public String getChevalRobe() {
		return chevalRobe;
	}

	public void setChevalRobe(String chevalRobe) {
		this.chevalRobe = chevalRobe;
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

	public String getCourseNom() {
		return courseNom;
	}

	public void setCourseNom(String courseNom) {
		this.courseNom = courseNom;
	}

	public Date getCourseDate() {
		return courseDate;
	}

	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}

	public Long getCourseHippodrome() {
		return courseHippodrome;
	}

	public void setCourseHippodrome(Long courseHippodrome) {
		this.courseHippodrome = courseHippodrome;
	}

	public Long getIdCourseToRun() {
		return idCourseToRun;
	}

	public void setIdCourseToRun(Long idCourseToRun) {
		this.idCourseToRun = idCourseToRun;
	}

	public List<Resultat> getResultats() {
		return resultats;
	}

	public void setResultats(List<Resultat> resultats) {
		this.resultats = resultats;
	}

	public DualListModel<Cheval> getCourseChevaux() {
		return courseChevaux;
	}

	public void setCourseChevaux(DualListModel<Cheval> courseChevaux) {
		this.courseChevaux = courseChevaux;
	}

}
