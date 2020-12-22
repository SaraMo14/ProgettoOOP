package info.univpm.jobs.statistics;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Interfaccia per le statistiche.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 * @param <T> Parametro di tipo generico, determinato poi nella classe che la implementerà..
 */
public interface Stats<T>
{
	/**
	 * Metodo astratto privo di implementazione, che verrà implementato successivamente a seconda delle nostre esigenze.
	 * (se fare statistiche su tutte le nazioni o una in particolare)
	 * @param body Filtro inserito dall'utente per elaborare le statistiche
	 * @param archivio HashMap contenente le offerte di lavoro.
	 * @return Ritorna Le statitiche effettuate sulle offerte di lavoro filtrate in base al body.
	 */
	public abstract T StatsJobs(JSONObject body, HashMap<String, Vector<JSONArray>> archivio);
	
}