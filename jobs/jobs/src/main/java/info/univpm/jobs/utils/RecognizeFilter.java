package info.univpm.jobs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import info.univpm.jobs.exceptions.FilterIllegalArgumentException;
import info.univpm.jobs.model.JobAdv;


public class RecognizeFilter {
	/**
	 * Metodo di conversione di parametri e filtri in una struttura HashMap, che ci faciliterà il lavoro
	 * di verifica di esattezza dei filtri e filtraggio stesso
	 * @param parameter Parametro a cui applicare il filtro
	 * @param filter Valore del filtro (di tipo object poiche distringuiamo i casi in cui l'utente specifica un solo
	 * 			valore del parametro - String- o più di uno - ArrayList<String>)
	 * @return result HashMap contenente
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String,ArrayList<String>> parseFilterOperator(String parameter, Object filter) {
		ArrayList<String> filters = new ArrayList<String>();
		HashMap<String, ArrayList<String>> result = new HashMap<>();
		if(filter instanceof String){
			filters.add((String) filter);
		}else{
			Map<String, Object> intermediate = new ObjectMapper().convertValue(filter, HashMap.class);
			for(Map.Entry<String, Object> entry: intermediate.entrySet()) {
				filters.add(entry.getKey());
				filters.addAll((ArrayList<String>)entry.getValue());
				//in realta il ciclo non serve ma basta una volta  sola
			}
		}
		result.put(parameter,filters);

		
		return result;
	}
	/**
	 * Metodo che, dato un parametro di ricerca, restituisce null se l'utente non l'ha specificato nel jsonBody, 
	 * o l'arraylist in caso contrario
	 *@param map mappa contenente il filtro specificato dall'utente
	 *@param parameter il parametro da controllare 
	 */
	public static  ArrayList<String> researchParameter( HashMap<String, ArrayList<String>> map, String parameter) {
		 ArrayList<String> result = new  ArrayList<String>();
		for(Map.Entry<String, ArrayList<String>> entry: map.entrySet()) {
			if(entry.getKey().equals(parameter)) 
					result=entry.getValue();
		}
		if(result.size()==0)
			result.add(0, null);//prevedo anche il caso in cui l'utente non specifichi la richiesta di un determinato parametro
		return result;
	}
}
