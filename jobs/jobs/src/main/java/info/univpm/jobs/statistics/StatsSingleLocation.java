package info.univpm.jobs.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

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
 * Classe che implementa l'interfaccia Stats<T>, adibita al calcolo delle statistiche nel caso in cui l'utente abbia
 * specificato una sola location
 *
 */
public class StatsSingleLocation implements Stats<int[]> {
	/**
	 * Metodo che dato il json body con location specifica, restituisce le statistiche relative alla location
	 * @param body JSON Body di parole chiave inserito dall'utente
	 * @param archivio HashMap popolato con lo storico dei dati
	 * @return oneLocStat Array di interi che contiente, per la data location, il numero di offerte di lavoro di ciascuna settimana che soddisfano la
	 * 			richiesta dell'utente
	 * */

	public int[] StatsJobs(JSONObject body, HashMap<String, Vector<JSONArray>> archivio) {
		int tempo = 0;
		if(body.get("months")!=null)
			tempo =Integer.parseInt((String) body.get("months"))*4;
		else
			tempo =Integer.parseInt((String) body.get("weeks"));
		ArrayList<String> listdescription = ParsingJson.ParsingToArrayListByDes( body);
			
		Vector<JSONArray> archiveLoc=archivio.get(body.get("location"));
		int[] weeklyNumberJobs=StatsUtils.matchingJobsArray(archiveLoc, listdescription, tempo);
		//double[] oneLocStats = {StatsUtils.minMaxAvg(weeklyNumberJobs)[0], StatsUtils.minMaxAvg(weeklyNumberJobs)[1], StatsUtils.minMaxAvg(weeklyNumberJobs)[2]};
		return weeklyNumberJobs;
	}

}
