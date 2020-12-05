package info.univpm.jobs.model;

/**
 * @author Riccardo Terrenzi
 *
 */
public class company {
	/**
	 * attributi della classe company
	 */
	private String name;
	private String url;
	/**
	 * @param name
	 * @param url
	 * costruttore company
	 */
	public company(String name, String url) {
		this.name = name;
		this.url = url;
	}

	/**
	 * @return
	 * getter name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name
	 * setter name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 * getter url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url
	 * setter url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
	
}
