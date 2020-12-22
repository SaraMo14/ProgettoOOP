package info.univpm.jobs.filters;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import info.univpm.jobs.exceptions.FilterIllegalArgumentException;
import info.univpm.jobs.services.url;
import info.univpm.jobs.utils.RecognizeFilter;

/**
 * Classe contenente metodi per il filtraggio delle offerte di lavoro in base alle parole chiave dell'utente
 * @author Terrenzi Riccardo
 * @author Montese Sara
 *
 */
public class Filter {
	
	/**
	 * Metodo ausiliario al metodo JsonBodyElaborationLoc
	 * @param body HashMap contenente il json Body inserito dall'utente
	 * @return result: Stringa contenente gli url filtrati in base alla parola chiave description
	 * @throws FilterIllegalArgumentException eccezione lanciata se l'operatore filtro specificato è errato
	 */
	
	public static String JsonBodyElaborationDes( HashMap<String, ArrayList<String>> body) throws FilterIllegalArgumentException{
		url Url = new url();
		ArrayList<String> description =  RecognizeFilter.researchParameter(body, "description");
		 if(description.size()==0 || description.get(0)==null) {
				 return Url.getUrlAllJobs()+"?";
		 }
		 if(description.size()==1) {
			 description.set(0, description.get(0).replace("+", "%2B"));
				 return Url.Des(description.get(0));
		 }
		 if(description.size()>=2) {
			 String values = "";
			 if(description.get(0).equals("$and")) {
				 for(int i = 1; i<description.size(); i++) {
					 description.set(i, description.get(i).replace("+", "%2B"));
						//il ciclo parte da 1 poiché al primo elemento c'è $and (tipo di filtro)
						 if(i!=description.size()-1)
							 values+=description.get(i)+ "+" ;
						 else
							 values+=description.get(i); 
					 }
				
					 return Url.Des(values); 
			 }else
				 throw new FilterIllegalArgumentException("Il filtro inserito è errato");
			 //ricordarsi di gestire l'eccezione
			 
		 }
		 return Url.getUrlAllJobs();
	}
	/**
	 * Metodo che, data la richiesta dell'utente, restituisce l'insieme degli url con cui ottenere i dati filtrati dal server
	 * @param body HashMap contenente il json Body inserito dall'utente
	 * @return result: ArrayList contenente gli url filtrati in base alla description, per ogni location
	 * @throws FilterIllegalArgumentException eccezione lanciata se l'operatore filtro specificato è errato
	 */
	public static ArrayList<String> JsonBodyElaborationLoc( HashMap<String, ArrayList<String>> body) throws FilterIllegalArgumentException{
		

		url Url = new url();
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> location = RecognizeFilter.researchParameter(body, "location");
		if(location.size()==0 || location.get(0)==null) {
			 result.add(Filter.JsonBodyElaborationDes(body));
		}else if(location.size()==1) {
			result.add(Url.Loc2(location.get(0),Filter.JsonBodyElaborationDes(body)));
		}else if(location.size()>=2) {
			
			if(location.get(0).equals("$in")) {
				for(int i = 1; i<location.size(); i++) {
				//	System.out.println(Url.Loc2(location.get(i),Filter.JsonBodyElaborationDes(body)));
					result.add(Url.Loc2(location.get(i),Filter.JsonBodyElaborationDes(body)));
				}
			}
			else  throw new FilterIllegalArgumentException("Il filtro inserito è errato");
		}
		return result;
	}
}
