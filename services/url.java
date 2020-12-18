package info.univpm.jobs.services;

public class url {

	private static	String urlAllJobs = "https://jobs.github.com/positions.json";
	
	public url() {
	}
	
	public static String Des(String des) {
		des=des.replace(" ","+");
		return urlAllJobs+ "?description=" + des;
	}
	
	public static String Loc(String loc) {
		loc=loc.replace(" ","+");
		return urlAllJobs+ "?location=" + loc;
	}
	
	public static String LocDes(String des, String loc) {
		loc=loc.replace(" ","+");
		des=des.replace(" ","+");
		return urlAllJobs+ "?description=" + des + "&location=" + loc;
	}

	public static String getUrlAllJobs() {
		return urlAllJobs;
	}

	public static void setUrlAllJobs(String urlAllJobs) {
		url.urlAllJobs = urlAllJobs;
	}
	
	public static String getId(String id) {
		return urlAllJobs + "/" + id;
	}
	public static String Loc2(String loc, String url) {
		if(loc!=null) {
			loc=loc.replace(" ","+");			
		return url+ "&location=" + loc;
		}else
			return urlAllJobs;
			
	}

	
}
