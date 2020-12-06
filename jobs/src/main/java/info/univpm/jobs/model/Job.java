package info.univpm.jobs.model;
/**
 * Rappresenta la classe che modella un lavoro 
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */

public class Job {
	/**
	 * Id del lavoro
	 */
	private String id;
	/**
	 * Tipo di lavoro
	 */
	private String type;
	/**
	 * Titolo del lavoro
	 */
    private String title;
    /**
	 * Descrizione del lavoro
	 */
	private String description;
	/**
	 * Azienda che offre lavoro
	 */
	private String company;
	/**
	 * Job place
	 */
    private String location;
    
    /**
	 * Costruttore della classe Job che inizializza un'istanza.
	 * @param id Id del lavoro
	 * @param type Tipo di lavoro
	 * @param title Titolo del lavoro
	 * @param description Descrizione del lavoro
	 * @param company Azienda che offre posizione lavorativa
	 * @param location Job place
	 */
	public Job(String id, String type, String title, String description, String company, String location) {
		this.id = id;
		this.type = type;
		this.title = title;
		this.description = description;
		this.company = company;
		this.location = location;
	}
	
	/** Getter dell'id del lavoro
	 * @return id Ritorna l'identificativo dell'offerta di lavoro
	 */
	public String getId() {
		return id;
	}
	/**
	 * Getter del tipo di offerta di lavoro
	 * @return type Ritorna il tipo di offerta di lavoro (Part-time, Full-Time...)
	 */
	public String getType() {
		return type;
	}
	/** Getter del titolo del lavoro
	 * @return title Ritorna il titolo della posizione lavorativa
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Getter della descrizione del lavoro
	 * @return description Ritorna la descrizione del lavoro
	 */
	public String getDescription() {
		return description;
	}
	/**Getter dell'azienda che offre il lavoro
	 * @return company Ritorna l'azienda che offre la posizione lavorativa
	 */
	public String getCompany() {
		return company;
	}
	/** Getter della location del lavoro
	 * @return location Ritorna il job place
	 */
	public String getLocation() {
		return location;
	}
	/** Setter dell'identificativo dell'offerta
	 * @param id Id passato dal chiamante
	 */
	public void setId(String id) {
		this.id = id;
	}
	/** Setter del tipo di offerta di lavoto
	 * @param type Tipo  passato dal chiamante
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**Setter del titolo dell'offerta
	 * @param title Titolo passato dal chiamante
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**Setter della descrizione dell'offerta di lavoro
	 * @param description  Descrizione passata dal chiamante
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**Setter del nome dell'azienda che offre il lavoro
	 * @param company  Nome dell'Azienda passata dal chiamante
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**Setter del job place
	 * @param location  Job place passato dal chiamante
	 */
	public void setLocation(String location) {
		this.location = location;
	}
		
	
}