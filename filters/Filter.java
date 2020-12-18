package info.univpm.jobs.filters;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import info.univpm.jobs.exceptions.FilterIllegalArgumentException;
import info.univpm.jobs.services.url;
import info.univpm.jobs.utils.RecognizeFilter;

public class Filter {
	
	/**
	 * Metodo ausiliario al metodo JsonBodyElaborationLoc
	 * @param body HashMap contenente il json Body inserito dall'utente
	 * @return result: Stringa contenente gli url filtrati in base alle parole chiave title, company e description
	 * @throws FilterIllegalArgumentException eccezione lanciata se l'operatore filtro specificato è errato
	 */
	
	public static String JsonBodyElaborationDes( HashMap<String, ArrayList<String>> body) throws FilterIllegalArgumentException{
		
		String  urlFinale="";
		
		String title = RecognizeFilter.researchParameter(body, "title").get(0);
		String company = RecognizeFilter.researchParameter(body, "company").get(0);
		
		if((title.isEmpty()==false) && (company.isEmpty()==false))
			urlFinale=title+"+"+company;
		else if(title.isEmpty()==false)
			urlFinale=title;
		else if(company.isEmpty()==false)
			urlFinale=company;
		
		//urlFinale=urlFinale.replace(" ","+");
		
		 ArrayList<String> description =  RecognizeFilter.researchParameter(body, "description");
		 if(description.size()==0) {
			 if(urlFinale=="") {
				 return url.getUrlAllJobs();
			 }
			 else return url.Des(urlFinale);
			 
		 }
		 if(description.size()==1) {
			 if(urlFinale=="") {
				 return url.Des(description.get(0));
			 }
			 else return url.Des(urlFinale+"+"+description.get(0));
		 }
		 if(description.size()>=2) {
			 String values = "";
			 if(description.get(0).equals("$and")) {
				 for(int i = 1; i<description.size(); i++) {
						//il ciclo parte da 1 poiché al primo elemento c'è $and (tipo di filtro)
						 if(i!=description.size()-1)
							 values+=description.get(i)+ "+" ;
						 else
							 values+=description.get(i); 
					 }
				 if(urlFinale=="") {
					 return url.Des(values);
				 }
				 else return url.Des(values + "+" + urlFinale);		 
			 }else
				 throw new FilterIllegalArgumentException("Il filtro inserito è errato");
			 //ricordarsi di gestire l'eccezione
			 
		 }
		 return url.getUrlAllJobs();
	}
	/**
	 * Metodo che, data la richiesta dell'utente, restituisce l'insieme degli url con cui ottenere i dati filtrati dal server
	 * @param body HashMap contenente il json Body inserito dall'utente
	 * @return result: ArrayList contenente gli url filtrati in base alla description, per ogni location
	 * @throws FilterIllegalArgumentException eccezione lanciata se l'operatore filtro specificato è errato
	 */
	public static ArrayList<String> JsonBodyElaborationLoc( HashMap<String, ArrayList<String>> body) throws FilterIllegalArgumentException{
		

		
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> location = RecognizeFilter.researchParameter(body, "location");
		if(location.size()==0) {
			 result.add(Filter.JsonBodyElaborationDes(body));
		}else if(location.size()==1) {
			result.add(url.Loc2(location.get(0),Filter.JsonBodyElaborationDes(body)));
		}else if(location.size()>=2) {
			
			if(location.get(0).equals("$in")) {
				for(int i = 1; i<location.size(); i++) {
					System.out.println(url.Loc2(location.get(i),Filter.JsonBodyElaborationDes(body)));
					result.add(url.Loc2(location.get(i),Filter.JsonBodyElaborationDes(body)));
				}
			}
			else  throw new FilterIllegalArgumentException("Il filtro inserito è errato");
		}
		return result;
	}
}
