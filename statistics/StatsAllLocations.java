package info.univpm.jobs.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Montese Sara
 * @author Terrenzi Riccardo
 *
 */
/**
 * Classe che implementa l'interfaccia Stats<T>, adibita al calcolo delle statistiche nel caso in cui l'utente abbia
 * specificato una sola location
 *
 */

public class StatsAllLocations implements Stats<HashMap<String, double[]>>{

	
	/**
	 * Metodo che dato il json body con location="all", lo sovrascrive ogni volta con una citta della lista 
	 * in modo da poter sfruttare il metodo analogo per la classe StatsSingleLocation.
	 * @param body JSON Body di parole chiave inserito dall'utente
	 * @param archivio HashMap popolato con lo storico dei dati
	 * @return allLocStats HashMap che contiente, per ogni Nazione, le relative statistiche di [min, max, avg] filtrate in base al body
	 */
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, double[]> StatsJobs(JSONObject body, HashMap<String, Vector<JSONArray>> archivio) { 
		HashMap<String, double[]>  allLocStats = new HashMap<String, double[]>();	
		Archive a = new Archive();	
		StatsSingleLocation stats = new StatsSingleLocation();
		for (int i=0; i<a.getListaPaesi().size(); i++) {
			body.remove("location");
			body.put("location" , a.getListaPaesi().get(i));
			allLocStats.put(a.getListaPaesi().get(i), stats.StatsJobs(body, archivio));
		}
		
		
		
		return allLocStats;
	}
}
