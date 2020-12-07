package info.univpm.jobs.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.el.parser.ParseException;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.model.JobAdv; //mai usato
/**
 * Classe per il download degli annunci di lavoro.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */

public class GetAndDecodeJob {

	/**
	 * ArrayList che contiene tutte le offerte di lavoro.
	 */
	private static ArrayList<JobAdv> jobsList = new ArrayList<JobAdv>();
	
	
	
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
	 * @return jobsList Ritorna al controller la lista dei lavori modellati.
	 * @throws NoJobFoundException Eccezione personalizzata per l'array di offerte di lavoro, se esso e' vuoto o e' NULL.
	 */
	
	public static ArrayList<JobAdv> getJobs(String url) throws NoJobFoundException	{
		try {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(urlAllJobs).openConnection();
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
			 System.out.println( data);
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
						 if(array.isEmpty() | array == null) throw new NoJobFoundException("Non ci sono offerte di lavoro in base a questi criteri");
					 }
					 catch(Exception e)
					 {
						 System.out.println("ERRORE. OPERAZIONE INTERROTTA NEL PRELEVAGGIO DEI PARAMETRI.");
						 System.out.println("MESSAGGIO: " + e.getMessage());
						 System.out.println("CAUSA: " + e.getCause());
					 }
					 System.out.println(array);
					 return array;
				 }
			 }
		}catch (IOException e)  //Parse exception
		 {
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

}