package info.univpm.jobs.model;

/**
 * La classe JobAdv (Advertisement) rappresenta una sottoclasse della classe Job
 * @author Montese Sara
 * @author Terrenzi Riccardo
 *
 */
public class JobAdv extends Job{
	
	/**
	 * Data di creazione del Job advertisement su GitHub jobs
	 */
	private String created_at;
	/**
	 * Url dell'azienda su GitHub jobs
	 */
	private String company_url;
	/**
	 * Url del Job advertisement su GitHub jobs
	 */
	private String job_url;
	
	
	
	/**
	 * Costruttore della classe JobAdv che inizializza un'istanza.
	 * Parametri per il costruttore della super classe Job
	 * @param id Id del lavoro
	 * @param type Tipo di lavoro
	 * @param title Titolo del lavoro
	 * @param description Descrizione del lavoro
	 * @param company Azienda che offre posizione lavorativa
	 * @param location Job place
	 * Parametri per il costruttore della classe JobAdv
	 * @param created_at Data di pubblicazione dell'annuncio
	 * @param company_url Url dell'azienda
	 * @param job_url Url dell'annuncio
	 */
	public JobAdv(String id, String type, String title, String description, String company, String location,
			String created_at, String company_url, String job_url) {
		super(id, type, title, description, company, location);
		this.created_at = created_at;
		this.company_url = company_url;
		this.job_url = job_url;
	}

	/**
	 * Getter della data di pubblicazione dell'annuncio
	 * @return created_at Ritorna la data di pubblicazione dell'annuncio.
	 */
	public String getCreated_at() {
		return created_at;
	}
	/**
	 * Getter dell'Url dell'azienda.
	 * @return company_url Ritorna l'Url dell'azienda.
	 */
	public String getCompany_url() {
		return company_url;
	}
	/**
	 * Getter dell'Url del job advertisement.
	 * @return job_url Ritorna l'Url del job advertisement.
	 */
	public String getJob_url() {
		return job_url;
	}
	/**
	 * Setter della data di creazione del job advertisement.
	 * @param created_at Data passata dal chiamante
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * Setter dell'Url dell'azienda.
	 * @param company_url Url aziendale passato dal chiamante.
	 */
	public void setCompany_url(String company_url) {
		this.company_url = company_url;
	}
	/**
	 * Setter dell'Url del job advertisement.
	 * @param job_url Url passato dal chiamante.
	 */
	public void setJob_url(String job_url) {
		this.job_url = job_url;
	}
	
	
}
