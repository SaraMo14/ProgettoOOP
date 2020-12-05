package info.univpm.jobs.model;

/**
 * @author Riccardo Terrenzi
 * @author Sara Montese
 */
public class JobClass {
	private String id;
	private String type;
	private String company;
	private String location;
	private String title;
	private String description;
	
	/**
	 * @param id
	 * @param type
	 * @param company
	 * @param location
	 * @param title
	 * @param description
	 * costruttore
	 */
	public JobClass(String id, String type, String company, String location, String title, String description) {
		super();
		this.id = id;
		this.type = type;
		this.company = company;
		this.location = location;
		this.title = title;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
