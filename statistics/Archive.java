package info.univpm.jobs.statistics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.services.GetAndDecodeJob;
import info.univpm.jobs.utils.StatsUtils;

/**
 * @author Montese Sara
 * @author Terrenzi Riccardo
 * Classe che rappresenta un archivio avente, per ogni Paese, le relative offerte di lavoro aggiornate dallo scheduler
 *
 */
public class Archive {
	private ArrayList<String> listaPaesi;
	private HashMap<String, Vector<JSONArray>> archivio;
	public Archive() {
		super();
		this.listaPaesi = new ArrayList<String>();
		listaPaesi.add("Germany");
		listaPaesi.add("UK");
		listaPaesi.add("Spain");
		listaPaesi.add("France");
		listaPaesi.add("India");
		listaPaesi.add("US");
		listaPaesi.add("Ukraine");
		listaPaesi.add("Netherlands");
		this.archivio = new HashMap<String, Vector<JSONArray>> ();

		for(String citta: this.listaPaesi) {
			archivio.put(citta, new Vector<JSONArray>());
		}
		 
	}
	public ArrayList<String> getListaPaesi() {
		return listaPaesi;
	}
	public HashMap<String, Vector<JSONArray>> getArchivio() {
		return archivio;
	}
	public void setArchivio(HashMap<String, Vector<JSONArray>> archivio) {
		this.archivio = archivio;
	}
	
	
	
	
	
	
	
	
}
