package info.univpm.jobs.model;

/**
 * @author Riccardo Terrenzi
 * @author Sara Montese
 *
 */
public class job_adv extends JobClass{
	/**
	 * data creazione annuncio di lavoro
	 */
	private String created_at;
	/**
	 * url dell'azienda
	 */
	private String company_url;
	/**
	 * url dell'annuncio di lavoro
	 */
	private String job_url;
	
	/**
	 * @param id
	 * @param type
	 * @param company
	 * @param location
	 * @param title
	 * @param description
	 * @param created_at
	 * @param company_url
	 * @param job_url
	 * costruttore
	 */
	public job_adv(String id, String type, String company, String location, String title, String description,
			       String created_at, String company_url, String job_url) {
		super(id, type, company, location, title, description);
		
		this.company_url = company_url;
		this.created_at = created_at;
		this.job_url = job_url;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getCompany_url() {
		return company_url;
	}

	public void setCompany_url(String company_url) {
		this.company_url = company_url;
	}

	public String getJob_url() {
		return job_url;
	}

	public void setJob_url(String job_url) {
		this.job_url = job_url;
	}
	
	
}
