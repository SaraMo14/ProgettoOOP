package info.univpm.jobs.services;

import java.io.BufferedReader;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.exceptions.NotEnoughDataException;
import info.univpm.jobs.model.JobAdv; //mai usato
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
	
	
	//SI POSSONO METTERE PIU URL DIVERSI PER DIVERSE STATISTICHE

	
	/**
	 * Metodo statico che preleva le offerte di lavoro dall'URL passato dal controller, successivamente
	 * effettua l'estrapolazione dei soli parametri che ci servono per creare il nostro oggetto
	 * Job, ed infine l'array di offerte modellato viene restituito al controller che ne effettua le dovute analisi.
	 * @param url URL delle API di GitHub.
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
			JSONArray objArray = (JSONArray) JSONValue.parseWithException(data);	//parse JSON Array
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
							/*if(array.isEmpty() || array == null) {
								 throw new NoJobFoundException("Non ci sono offerte di lavoro in base a questi criteri");
							 }*/
						 }
						 
						/*catch(NoJobFoundException e){
								System.out.println(e);
							}*/
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
	
	public JSONArray getJobsByLoc( String loc) throws NoJobFoundException {
	String url1 = urlAllJobs+ "?location=" + loc;
	JSONArray a = getJobs(url1);
	return a;
	}
	
	public JSONArray getJobsByDesAndLoc( String des, String Loc) throws NoJobFoundException {
		String url1 = urlAllJobs+ "?description=" + des + "&location=" + Loc;
		JSONArray a = getJobs(url1);
		return a;
	}
	
	public JSONArray getJobsByDes( String des) throws NoJobFoundException {
		String url1 = urlAllJobs+ "?description=" + des;
		JSONArray a = getJobs(url1);
		return a;
		}
	
	public JSONArray getJobsByLocbody( JSONObject a) throws NoJobFoundException {
		/*String loc = (String) a.get("location");
		String des = (String) a.get("description");
		String url1 = urlAllJobs+ "?location=" + loc + "&description=" + des;
		JSONArray b = GetAndDecodeJob.getJobs(url1);*/
		
		String url1 = url.LocDes((String) a.get("description"), (String) a.get("location"));
		JSONArray b = getJobs(url1); 
		return b;
		}
	
	public JSONArray getAllPages() throws NoJobFoundException, org.json.simple.parser.ParseException {
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
					/*catch(NoJobFoundException e){
							System.out.println(e);
						}*/
					 catch(Exception e)
					 {
						 System.out.println("ERRORE. OPERAZIONE INTERROTTA NEL PRELEVAGGIO DEI PARAMETRI.");
						 System.out.println("MESSAGGIO: " + e.getMessage());
						 System.out.println("CAUSA: " + e.getCause());
					 }
				 }
			 } 
			
			//listaDeiJobAdv = (JSONArray) JSONValue.parseWithException(ParsingJson.ParsingToJSON(array));
			 //array.clear();
			 //System.out.println(listaDeiJobAdv.get(0).toString());
			 finalArray.addAll(array);
			}
			i++;
		}while(a!=null);//(!a.isEmpty());	
		
		if(array.isEmpty()) 
			 throw new NoJobFoundException("Non ci sono offerte di lavoro");
		else {	
			listaDeiJobAdv = (JSONArray) JSONValue.parseWithException(ParsingJson.ParsingToJSON(finalArray));
			return listaDeiJobAdv;
		}
	}
	
	public JSONArray getJobById(String id) throws NoJobFoundException {
		String url1 = url.getId(id);
		JSONArray a = getJobs(url1);
		return a;
		}
	@SuppressWarnings("unchecked")
	public JSONArray getJobByBody(ArrayList<String> body) throws NoJobFoundException {
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
		
		int nWeeks = Integer.parseInt((String) jsonString.get("weeks"));
		int qtaDati=0;
		JSONArray finalStats = new JSONArray();
		if(jsonString.get("location").equals("all")==false) {
			qtaDati=archivio.get(jsonString.get("location")).size();
			if(nWeeks>qtaDati)
				throw new NotEnoughDataException("Non sono presenti dati a sufficienza per eseguire tali statistiche");				
			
			StatsSingleLocation stats= new StatsSingleLocation();
			double[] result = stats.StatsJobs(jsonString, archivio);
			JSONObject obj = ParsingJson.MaptoJSONObject(jsonString.get("location").toString(), jsonString.get("description"),result);
			
			finalStats.add(obj);
		}else {
			
			double[] valoriMedi = new double[archivio.size()];
			for(Entry<String, Vector<JSONArray>> value : archivio.entrySet()) {
				qtaDati = value.getValue().size();
				break; //cicla una sola volta, solo per leggere la dimensione del primo vector (relativo alla prima citta), 
						//tanto le successive avranno la stessa size
			}
			if(nWeeks>qtaDati)
				throw new NotEnoughDataException("Non sono presenti dati a sufficienza per eseguire tali statistiche");				
			
			StatsAllLocations stats= new StatsAllLocations();
			
			HashMap<String, double[]> result= stats.StatsJobs(jsonString, archivio);		
			
			int i=0;
			for(Entry<String, Vector<JSONArray>> value : archivio.entrySet()) {
				JSONObject obj = ParsingJson.MaptoJSONObject(value.getKey(), jsonString.get("description"),result.get(value.getKey()));
				finalStats.add(obj);
				valoriMedi[i]=result.get(value.getKey())[2];
				i++;
			}
			
			double medioMax= StatsUtils.maxDouble(valoriMedi);
			JSONObject medioObj = new JSONObject();
			medioObj.put("maximum avg", medioMax);
			finalStats.add(medioObj);
		}
		return finalStats;
		
	}
	
	public JSONArray getCountryList() throws ParseException {
		Archive a = new Archive();
		ArrayList<String> countries = a.getListaPaesi();
		return (JSONArray) JSONValue.parseWithException(ParsingJson.ParsingToJSONString(countries));
	}
}