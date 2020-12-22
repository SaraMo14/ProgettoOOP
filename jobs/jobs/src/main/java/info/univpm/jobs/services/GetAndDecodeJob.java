package info.univpm.jobs.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.exceptions.NotEnoughDataException;
import info.univpm.jobs.model.JobAdv;
import info.univpm.jobs.statistics.Archive;
import info.univpm.jobs.statistics.StatsAllLocations;
import info.univpm.jobs.statistics.StatsSingleLocation;
import info.univpm.jobs.utils.ParsingJson;
import info.univpm.jobs.utils.StatsUtils;
/**
 * Classe per il download degli annunci di lavoro.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */


@Service
public class GetAndDecodeJob {
	
	
	/**
	 * Stringa statica contenente l'URL della API di GitHub per richiedere tutte le offerte di lavoro
	 */
	private static	String urlAllJobs = "https://jobs.github.com/positions.json";
	

	
	/**
	 * Metodo  che preleva le offerte di lavoro dall'URL passato dal controller, successivamente
	 * effettua l'estrapolazione dei soli parametri che ci servono per creare il nostro oggetto
	 * Job, ed infine l'array di offerte modellato viene restituito al controller che ne effettua le dovute analisi.
	 * @param url URL dell'API di GitHub Jobs.
	 * @return listaDeiJobAdv Ritorna al controller il JSONArray dei lavori modellati.
	 * @throws NoJobFoundException Eccezione personalizzata per l'array di offerte di lavoro, se esso e' vuoto o e' NULL.
	 */
	
	public JSONArray getJobs(String url) throws NoJobFoundException{
		try {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
	         openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();
			
			String data = "";
			 String line = "";
			 try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				  data+= line;
			   }
			 } 
			 finally {
			   in.close();
			 }
			JSONArray objArray = (JSONArray) JSONValue.parseWithException(data);	
			ArrayList<JobAdv> array = new ArrayList<JobAdv>();
			for(Object o: objArray)
			 {
				 if (o instanceof JSONObject)
				 {
					 JSONObject o1 = (JSONObject) o; 
						 try
						 {
							 String ID = (String) o1.get("id");
							 String jobType = (String) o1.get("type");
							 String jobTitle = (String) o1.get("title");
							 String jobDescription = (String) o1.get("description");
							 String jobCompany = (String) o1.get("company");
							 String jobLocation = (String) o1.get("location");
							 String creationDate = (String) o1.get("created_at");
							 String company_url = (String) o1.get("company_url");
							 String job_url = (String) o1.get("url");
							 
							 JobAdv j1 = new JobAdv(ID, jobType, jobTitle, jobDescription,
									 jobCompany, jobLocation, creationDate, company_url, job_url);
							array.add(j1);							 
						 }
						 catch(Exception e)
						 {
							 System.out.println("ERRORE. OPERAZIONE INTERROTTA NEL PRELEVAGGIO DEI PARAMETRI.");
							 System.out.println("MESSAGGIO: " + e.getMessage());
							 System.out.println("CAUSA: " + e.getCause());
						 }
					 }
			 }
			if(array.isEmpty()) 
				 throw new NoJobFoundException("Non ci sono offerte di lavoro in base a questi criteri");
			else {
				JSONArray listaDeiJobAdv = new JSONArray();
				listaDeiJobAdv = (JSONArray) JSONValue.parseWithException(ParsingJson.ParsingToJSON(array));
				 array.clear();
				 return listaDeiJobAdv;
			}
		}
		catch (IOException e) {  //Parse exception{
			 System.out.println("Errore nell'operazione di I/O.");
			 System.out.println("Messaggio: " + e.getMessage());
			 System.out.println("Causa: " + e.getCause());
		 }
		catch (Exception e) { 
		System.out.println("Messaggio: " + e.getMessage());
		 System.out.println("Causa: " + e.getCause());
		}
		return null;
	}
	
	/**
	 * Metodo che data una location, restituisce tutte le offerte di lavoro relative
	 *@param loc Location specifica
	 *@return JSONArray delle offerte di lavoro
	 *@throws NoJobFoundException Eccezione personalizza lanciata nel caso in cui non ci siano offerte di lavoro con tale location
	 */
	public JSONArray getJobsByLoc( String loc) throws NoJobFoundException {
	String url1 = urlAllJobs+ "?location=" + loc;
	JSONArray a = getJobs(url1);
	return a;
	}
	
	/**
	 * Metodo che data una location e una description, restituisce tutte le offerte di lavoro relative
	 *@param loc Location specifica
	 **@param des Description specifica
	 *@return JSONArray delle offerte di lavoro
	 *@throws NoJobFoundException Eccezione personalizza lanciata nel caso in cui non ci siano offerte di lavoro con tale location e descrption
	 */
	public JSONArray getJobsByDesAndLoc( String des, String Loc) throws NoJobFoundException {
		String url1 = urlAllJobs+ "?description=" + des + "&location=" + Loc;
		JSONArray a = getJobs(url1);
		return a;
	}
	
	/**
	 * Metodo che data una description, restituisce tutte le offerte di lavoro relative
	 **@param des Description specifica
	 *@return JSONArray delle offerte di lavoro
	 *@throws NoJobFoundException Eccezione personalizza lanciata nel caso in cui non ci siano offerte di lavoro con tale  descrption
	 */
		public JSONArray getJobsByDes( String des) throws NoJobFoundException {
		String url1 = urlAllJobs+ "?description=" + des;
		JSONArray a = getJobs(url1);
		return a;
		}
	
	
		
		public JSONArray getJobsByLocbody( JSONObject a) throws NoJobFoundException {
		url Url = new url();
		String url1 = Url.LocDes((String) a.get("description"), (String) a.get("location"));
		JSONArray b = getJobs(url1); 
		return b;
		}
	
	/**
	 * Metodo  che tutte preleva le offerte di lavoro disponibili sull'API
	 * @return listaDeiJobAdv Ritorna al controller il JSONArray dei tutti i lavori.
	 * @throws NoJobFoundException Eccezione personalizzata per l'array di offerte di lavoro, se esso e' vuoto.
	 */
	public JSONArray getAllPages() throws ParseException, NoJobFoundException {
		int i=0;
		JSONArray a =  new JSONArray();
		JSONArray listaDeiJobAdv = new JSONArray();

		ArrayList<JobAdv> array = new ArrayList<JobAdv>();
		ArrayList<JobAdv> finalArray = new ArrayList<JobAdv>();
		do {
			String url0 = "https://jobs.github.com/positions.json?page="+i;
			a = getJobs(url0);
			if(a!=null) {
		
				for(Object o: a){
				 if (o instanceof JSONObject){
					 JSONObject o1 = (JSONObject) o; 
					 try
					 {
						 String ID = (String) o1.get("id");
						 String jobType = (String) o1.get("type");
						 String jobTitle = (String) o1.get("title");
						 String jobDescription = (String) o1.get("description");
						 String jobCompany = (String) o1.get("company");
						 String jobLocation = (String) o1.get("location");
						 String creationDate = (String) o1.get("created_at");
						 String company_url = (String) o1.get("company_url");
						 String job_url = (String) o1.get("url");
						 
						 JobAdv j1 = new JobAdv(ID, jobType, jobTitle, jobDescription,
								 jobCompany, jobLocation, creationDate, company_url, job_url);
						array.add(j1);
						// if(array.isEmpty() | array == null) throw new NoJobFoundException("Non ci sono offerte di lavoro in base a questi criteri");
					 }
					catch(Exception e)
					 {
						 System.out.println("ERRORE. OPERAZIONE INTERROTTA NEL PRELEVAGGIO DEI PARAMETRI.");
						 System.out.println("MESSAGGIO: " + e.getMessage());
						 System.out.println("CAUSA: " + e.getCause());
					 }
				 }
			 } 
			 finalArray.addAll(array);
			}
			i++;
		}while(a!=null);//(!a.isEmpty());	
		if(finalArray.isEmpty()) 
			 throw new NoJobFoundException("Non ci sono offerte di lavoro");
		else {	
			listaDeiJobAdv = (JSONArray) JSONValue.parseWithException(ParsingJson.ParsingToJSON(finalArray));
			return listaDeiJobAdv;
		}
	}
	
/*	public JSONArray getJobById(String id) throws NoJobFoundException {
		url Url = new url();
		String url1 = Url.getId(id);
		JSONArray a = getJobs(url1);
		return a;
		}
	*/
	
	//Da chiamare direttamente se l'utente non specifica title e company nel body
	/**metodo che, dato in ingresso un arraylist contenente gli url generati in base al body inserito dall'utente, restituisce il JSONArray risultante
	 * dalle varie chiamate all'API
	 * @param body url generati in base al body inserito dall'utente
	 * @return JSONArray popolato delle offerte di lavoro filtrate
	 * @throws NoJobFoundException Eccezione lanciata se non ci sono offerte di lavoro
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getJobByBody(ArrayList<String> body) throws NoJobFoundException, ParseException {
		
		if(body.get(0).equals(urlAllJobs+ "?")){
			return getAllPages();
		}
		JSONArray result = new JSONArray();
		for(String filter: body) {
			JSONArray a = getJobs(filter);
			if(a!=null)
				result.addAll(a);
		}
		if(result.isEmpty() || body == null)
			 throw new NoJobFoundException("Non ci sono offerte di lavoro in base a questi criteri");
		return result;
		}
	
	
	@SuppressWarnings("unchecked")
	public JSONArray getStatsByBody(String param) throws NotEnoughDataException, ParseException {
		JSONObject jsonString = (JSONObject) JSONValue.parseWithException(param);
		HashMap<String, Vector<JSONArray>> archivio = StatsUtils.leggiFile("./archivio.txt");
		int tempo=0;
		if(jsonString.get("months")!=null) {
			tempo = Integer.parseInt((String) jsonString.get("months"))*4;
			
		}else {
			tempo= Integer.parseInt((String) jsonString.get("weeks"));
		}
		
		int qtaDati=0;
		JSONArray finalStats = new JSONArray();
		//Se l'utente non specifica la location, la impostiamo "all"
		if(jsonString.get("location")==null)
			jsonString.put("location", "all");
		
		if(jsonString.get("location").equals("all")==false && jsonString.get("location").equals("All")==false){
			
			jsonString.replace("location", StatsUtils.lowerAndUpper(jsonString.get("location").toString()));
			qtaDati=archivio.get(jsonString.get("location")).size();
			if(tempo>qtaDati)
				throw new NotEnoughDataException("Non sono presenti dati a sufficienza per eseguire tali statistiche");				
			
			StatsSingleLocation stats= new StatsSingleLocation();
			//double[] result = stats.StatsJobs(jsonString, archivio);
			int[] result;
			if(jsonString.get("months")!=null) {
				result = StatsUtils.weeksToMonths(stats.StatsJobs(jsonString, archivio));
			}else {
				result = stats.StatsJobs(jsonString, archivio);
			}
			JSONObject obj;
			if(jsonString.get("months")!=null)
				 obj = ParsingJson.MaptoJSONObject(jsonString.get("location").toString(), jsonString.get("description"),result, "month");
			else
				 obj = ParsingJson.MaptoJSONObject(jsonString.get("location").toString(), jsonString.get("description"),result, "week");
			finalStats.add(obj);
		}else {
			
			double[] valoriMedi = new double[archivio.size()];
			for(Entry<String, Vector<JSONArray>> value : archivio.entrySet()) {
				qtaDati = value.getValue().size();
				break; //cicla una sola volta, solo per leggere la dimensione del primo vector (relativo alla prima citta), 
						//tanto le successive avranno la stessa size
			}
			if(tempo>qtaDati)
				throw new NotEnoughDataException("Non sono presenti dati a sufficienza per eseguire tali statistiche");				
			
			StatsAllLocations stats= new StatsAllLocations();
			
			HashMap<String, int[]> result= stats.StatsJobs(jsonString, archivio);
			if(jsonString.get("months")!=null) {
				for(Entry<String, int[]> value : result.entrySet()) {
					value.setValue(StatsUtils.weeksToMonths(value.getValue()));
				}
			}
				//result = StatsUtils.weeksToMonths(result); 
			
			int i=0;
			for(Entry<String, Vector<JSONArray>> value : archivio.entrySet()) {
				JSONObject obj;
				if(jsonString.get("months")!=null)
					obj= ParsingJson.MaptoJSONObject(value.getKey(), jsonString.get("description"),result.get(value.getKey()), "month");
				else
					obj= ParsingJson.MaptoJSONObject(value.getKey(), jsonString.get("description"),result.get(value.getKey()), "week");
				double[] oneLocStats = {StatsUtils.minMaxAvg(result.get(value.getKey()))[0], StatsUtils.minMaxAvg(result.get(value.getKey()))[1], StatsUtils.minMaxAvg(result.get(value.getKey()))[2]};
				finalStats.add(obj);
				valoriMedi[i]=oneLocStats[2];
				i++;
			}
			
			double medioMax= StatsUtils.maxDouble(valoriMedi);
			JSONObject medioObj = new JSONObject();
			medioObj.put("maximum avg", medioMax);
			finalStats.add(medioObj);
		}
		return finalStats;
		
	}
	/** Metodo che legge dall'archivio la lista delle Nazioni su cui fare la statistica
	 * @return JSONArray con la lista delle Nazioni
	 */
	public JSONArray getCountryList() throws ParseException {
		Archive a = new Archive();
		ArrayList<String> countries = a.getListaPaesi();
		return (JSONArray) JSONValue.parseWithException(ParsingJson.ParsingToJSON(countries));
	}
	
	/** Metodo che legge dal file instructions.txt le istruzioni all'uso del programma
	 * @return Vector di stringhe contenente le istruzioni
	 */
	public Vector <String>  getInstructions() {
		Vector <String> rotte = new Vector <String>();
		try {
			Scanner file_input = new Scanner(new BufferedReader(new FileReader("instructions.txt")));
			while (file_input.hasNext ()) {
				rotte.add(file_input.nextLine());
				}
			file_input.close();
		}
		catch ( IOException e) { 
		System.out.println(" ERRORE di I/O");
		System.out.println(e);
		}
		return rotte;
	}
	
	/**
	 * @param url Url risultante da filtraggio in base a Location e Description
	 * @param request title o company specificato nel body dall'utente
	 * @param field title o company
	 * @return JSONArray filtrato 
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getJobByTitleAndCompany(JSONArray result, String request, String field) throws NoJobFoundException{
		request=request.toLowerCase();
		JSONArray finalArray = new JSONArray();
		for(Object o: result)
		 {
		  if(o instanceof JSONObject) {
			  JSONObject o1 = (JSONObject) o; 
			  String param = (String) o1.get(field);
			  param=param.toLowerCase();
			  if(param.contains(request)){
				finalArray.add(o1);
			}
		  }
		 }
		if(finalArray.isEmpty())
			throw new NoJobFoundException("Non ci sono offerte di lavoro in base a questi criteri ");
		return finalArray;
	}
}