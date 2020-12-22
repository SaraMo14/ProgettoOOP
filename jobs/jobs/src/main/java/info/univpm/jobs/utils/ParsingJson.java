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
	 * Metodo statico che passato un arrayList lo converte nel formato JSON.
	 * @param param Lista da convertire.
	 * @return outFinal Contiene l'arrayList convertito nel formato JSON, in modo che il web server possa comprenderlo.
	 */
	public static String ParsingToJSON(ArrayList<?> param)
	{
		Gson out = new GsonBuilder().setPrettyPrinting().create();
		String outFinal = out.toJson(param);
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
		 
	
	
	
	
	/**Metodo statico che crea una Map partendo dai parametri su cui è stata fatta la statistica, e la converte in JSONObject
	 * @param location location su cui è stata fatta la statistica
	 * @param description Parole chiave su cui è stata fatta la statistica
	 * @param values Vettore di interi contenente le offerte di lavoro di ciascuna settimana
	 * @return jsonObj Statistica contenuta nella Map, convertita in un jSONObject
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public static JSONObject MaptoJSONObject(String location, Object description, int[] values, String field) {
	 
		Map<String, Object> obj=new HashMap<String, Object>(); 
		obj.put("location", location);
		obj.put("description",description);
		
		
		JSONObject innerObj = new JSONObject();		
		for(int i=0; i<values.length; i++) {
			//popolo al contrario poiche la week 1 riguarda la settimana meno recente, dunque si trova in fondo all'array values
			int label = i+1;
			innerObj.put(field + " " + label, values[values.length-i-1]);
		}
		obj.put("Valori periodici:", innerObj);
		
		double[] stats = {StatsUtils.minMaxAvg(values)[0], StatsUtils.minMaxAvg(values)[1], StatsUtils.minMaxAvg(values)[2]};
		
		JSONObject innerObj2 = new JSONObject();
		innerObj2.put("min", stats[0]);
		innerObj2.put("max", stats[1]);
		innerObj2.put("avg", stats[2]);
		
		obj.put("Statistiche",innerObj2);
		
		JSONObject jsonObj = new JSONObject(obj);
		return jsonObj;
 }
	

}