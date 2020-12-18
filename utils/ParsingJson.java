package info.univpm.jobs.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.boot.json.JsonParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import info.univpm.jobs.model.JobAdv;
/**
 * Classe per effettuare conversioni che includono il formato JSON.
 * Fa uso della libreria esterna GSON
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */
public class ParsingJson{
	/**
	 * Metodo statico che passato l'arrayList dei lavori modellati, lo converte nel formato JSON.
	 * @param jobsArray Lista di array contenente le offerte di lavoro modellate.
	 * @return outFinal Contiene l'arrayList convertito nel formato JSON, in modo che il web server possa comprenderlo.
	 */
	public static String ParsingToJSON(ArrayList<JobAdv> jobsArray)
	{
		Gson out = new GsonBuilder().setPrettyPrinting().create();
		String outFinal = out.toJson(jobsArray);
		return outFinal;
	}
	
	/**
	 * Metodo statico che passato l'arrayList dei lavori modellati, lo converte nel formato JSON.
	 * @param jobsArray Lista di array contenente le offerte di lavoro modellate.
	 * @return outFinal Contiene l'arrayList convertito nel formato JSON, in modo che il web server possa comprenderlo.
	 */
	public static String ParsingToJSONString(ArrayList<String> countries)
	{
		Gson out = new GsonBuilder().setPrettyPrinting().create();
		String outFinal = out.toJson(countries);
		return outFinal;
	}
	
	/**
	 * Metodo statico che converte il JSONArray della description in ArrayList.
	 * @param body jsonArray da convertire.
	 * @return listdescription Contiene il JSON convertito nel formato ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> ParsingToArrayListByDes(JSONObject body)
	{
		ArrayList<String> listdescription = new ArrayList<String>();     
		JSONArray jArray = (JSONArray)body.get("description"); 
		if (jArray != null) { 
			listdescription.addAll(jArray);
		} 
		return listdescription;
	}
	
	
	/**
	 * MEtodo statico per la conversione di un JSONString (in particolare la richiesta dell'utente riguardo le statistiche) in MAP
	 * @param jsonString JSONString da convertire
	 * @return myMap Map derivante dalla conversione
	 * @throws JsonParseException 
	 */
	public static Map<String, Object> JSONStringToMap(String jsonString) throws JsonParseException {
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> myMap = new Gson().fromJson(jsonString, type);
		return myMap;
	 }
		 
	
	
	
	/**Metodo statico che crea una Map partendo dai parametri su cui è stata fatta la statistsica, e la converte in JSONObject
	 * @param location location su cui è stata fatta la statistica
	 * @param description Parole chiave su cui è stata fatta la statistica
	 * @param values Vettore di double contenente la statistica [min, max, avg]
	 * @return jsonObj Statistica contenuta nella Map, convertita in un jSONObject
	 */
	public static JSONObject MaptoJSONObject(String location, Object description, double[] values) {
	 
		Map<String, Object> obj=new HashMap<String, Object>(); 
		obj.put("location", location);
		obj.put("description",description);
		obj.put("min", values[0]);
		obj.put("max", values[1]);
		obj.put("avg", values[2]);
		
		JSONObject jsonObj = new JSONObject(obj);
		return jsonObj;
 }
}