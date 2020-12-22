package info.univpm.jobs.services;


/**
 * @author Montese Sara
 * @author Terrenzi Riccardo
 * Classe che genera gli url per acquisire i dati dall'API a seconda delle necessità.
 *
 */
public class url {

	private static	String urlAllJobs = "https://jobs.github.com/positions.json";
	
	public url() {
	}
	
	public String Des(String des) {
		des=des.replace(" ","+");
		return urlAllJobs+ "?description=" + des;
	}
	
	public String Loc(String loc) {
		loc=loc.replace(" ","+");
		return urlAllJobs+ "?location=" + loc;
	}
	
	public String LocDes(String des, String loc) {
		loc=loc.replace(" ","+");
		des=des.replace(" ","+");
		return urlAllJobs+ "?description=" + des + "&location=" + loc;
	}

	public String getUrlAllJobs() {
		return urlAllJobs;
	}

	public void setUrlAllJobs(String urlAllJobs) {
		url.urlAllJobs = urlAllJobs;
	}
	
	public String getId(String id) {
		return urlAllJobs + "/" + id;
	}
	public String Loc2(String loc, String url) {
		if(loc!=null) {
			loc=loc.replace(" ","+");			
		return url+ "&location=" + loc;
		}else
			return urlAllJobs;
			
	}

	
}
