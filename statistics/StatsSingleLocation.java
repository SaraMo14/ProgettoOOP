package info.univpm.jobs.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.model.JobAdv;
import info.univpm.jobs.utils.ParsingJson;
import info.univpm.jobs.utils.StatsUtils;
 
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
public class StatsSingleLocation implements Stats<double[]> {
	/**
	 * Metodo che dato il json body con location specifica, restituisce le statistiche relative alla loca
	 * @param body JSON Body di parole chiave inserito dall'utente
	 * @param archivio HashMap popolato con lo storico dei dati
	 * @return oneLocStat Array di 3 double che contiente, per la data location, le relative statistiche di [min, max, avg] filtrate in base al body
	 */
	public double[] StatsJobs(JSONObject body, HashMap<String, Vector<JSONArray>> archivio) {
		int nWeeks =Integer.parseInt((String) body.get("weeks"));
		ArrayList<String> listdescription = ParsingJson.ParsingToArrayListByDes( body);
		Vector<JSONArray> archiveLoc=archivio.get(body.get("location"));
		int[] weeklyNumberJobs=StatsUtils.matchingJobsArray(archiveLoc, listdescription, nWeeks);
		double[] oneLocStats = {StatsUtils.min(weeklyNumberJobs), StatsUtils.max(weeklyNumberJobs), StatsUtils.avg(weeklyNumberJobs)};
		return oneLocStats;
	}

	
}
