package info.univpm.jobs.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.services.GetAndDecodeJob;
import info.univpm.jobs.statistics.Stats;

/**
 * Classe che contiene i metodi ausiliari per il calcolo delle statistiche.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 *
 */

public class StatsUtils {
	/**
	 * Metodo per appendere al vector di jsonArray le nuove offerte di lavoro rispetto ad una data nazione
	 * Ha lo scopo di tenere aggiornato l'archivio e viene invocato dallo Scheduler
	 */
	public static HashMap<String, Vector<JSONArray>> fillHashMap(HashMap<String, Vector<JSONArray>> archivio) throws NoJobFoundException{
		GetAndDecodeJob getAndDecodeJob = new GetAndDecodeJob();
		for (Map.Entry<String, Vector<JSONArray>> entry : archivio.entrySet()) { 
			entry.getValue().add(getAndDecodeJob.getJobsByLoc(entry.getKey()));
		}
		return archivio;
	}
	
	/**
	 * Metodo per salvare un HashMap in un file binario.
	 * 
	 * @param nome_file Nome del file in cui salvare l'oggetto.
	 * @param archivio HashMap da salvare contenente l'archivio aggiornato dei dati
	 */
	public static void scriviFile(String nome_file, HashMap<String, Vector<JSONArray>> archivio) {
		try {
			ObjectOutputStream file_output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nome_file)));
			file_output.writeObject(archivio);
			file_output.close();
			//System.out.println("File salvato!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per leggere un oggetto serializzabile da un file binario. * 
	 * @param nome_file Nome del file da cui leggere l'oggetto.
	 * @return archivio La hashMap aggiornata dal demone
	 	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Vector<JSONArray>> leggiFile(String nome_file) {
		try {
			ObjectInputStream file_input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nome_file)));
			
			HashMap<String, Vector<JSONArray>> archivio = (HashMap<String, Vector<JSONArray>>) file_input.readObject();
				//System.out.println("HashMap letto: "+ archivio);
				//System.out.println("HashMap letto ha "+ archivio.size()+" elementi!");
			
			file_input.close();
			return archivio;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo per calcolare il valore il massimo di un vettore di interi. * 
	 * @param array Nome del vettore di cui calcolare l'intero.
	 * @return max Valore massimo
	 	 */
	 public static int max( int[] array ) {
		int max = array[0];
		for(int i=0; i<array.length; i++) {
			if(array[i]>max) {
				max = array[i];
			}
		}
		return max;
	}
	 /**
		 * Metodo per calcolare il valore il massimo di un vettore di double. 
		 * @param array Nome del vettore di cui calcolare l'intero.
		 * @return max Valore massimo
		 	 */
	 public static double maxDouble( double[] array ) {
			double max = array[0];
			for(int i=0; i<array.length; i++) {
				if(array[i]>max) {
					max = array[i];
				}
			}
			return max;
		}
	
	/**
	 * Metodo per calcolare il valore il minimo di un vettore di interi. 
	 * @param array Nome del vettore di cui calcolare l'intero.
	 * @return min Valore massimo
	 	 */
	  public static int min( int[] array ) {
		int min = array[0];
		for(int i=0; i<array.length; i++) {
			if(array[i]<min) {
				min = array[i];
			}
		}
		return min;
	}
	
	/**
	 * Metodo per calcolare il valore medio di un vettore di interi. 
	 * @param array Nome del vettore di cui calcolare l'intero.
	 * @return avg Valore medio calcolato
	 	 */
	 public static double avg( int[] array ) {
		int somma=0;
		for (int i=0; i<array.length; i++ ) {
			somma+=array[i];
		}
		return (double)somma/array.length;
	}
	 
	 
	 
	/** Metodo che verifica, per il numero di settimane specificato dall'utente (su cui fare la statistica), 
	 * quante offerte di lavoro matchano le parole chiave specificate.
	 * @param archiveLoc Vector di JSONArray contenente lo storico dei dati rispetto alla location scelta dall'utente
	 * @param listdescription ArrayList contenente le parole chiave specificate dall'utente
	 * @param nWeeks n. settimane su cui effettuare la statistica
	 * @return weeklyNumberJobs Un vettore di interi contenente il numero di offerte di lavoro, per settimana, che soddisfano la
	 * 			richiesta dell'utente
	 */
	public static int[] matchingJobsArray (Vector<JSONArray> archiveLoc, ArrayList<String> listdescription, int nWeeks){
		int[] weeklyNumberJobs = new int[nWeeks];
		int j=0;
			int counter=0;		
			for(int i = archiveLoc.size()-1; i>archiveLoc.size()-nWeeks-1; i--) { 
				//System.out.println("ciclo n. "+ i+"--> "+ archiveLoc.get(i).toJSONString());
				//System.out.println(i);
				counter=0;
				for(Object o: archiveLoc.get(i))
				 {
					
					 if (o instanceof JSONObject)
					 {
						 JSONObject o1 = (JSONObject) o; 
						 String jobDescription = (String) o1.get("description");
						 if(matchRequest(listdescription, jobDescription) ==true) {
							 counter++;
							 //System.out.println(o1.toJSONString());
						 }
							 
					 }
				 } 
				weeklyNumberJobs[j] = counter;
				j++;
			}
		
		return weeklyNumberJobs;
	}
	/**
	 * Metodo per verificare che un elemento del Vector soddisfa la richiesta dell'utente
	 * @param request Descrizione in base alla quale si vuole effettuare la statistica.
	 *  @param description Stringa  da controllare.
	 * @return Ritorna true se l'elemento matcha la richiesta, false in caso contrario.
	 */
	public static boolean matchRequest(ArrayList<String> request, String description) {
		for(String elem: request) {
			if(description.toLowerCase().contains(elem.toLowerCase())==false)
				return false;
		}
		return true;
	}
	
	
}
