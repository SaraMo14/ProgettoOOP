package info.univpm.jobs.services;

import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.statistics.Archive;
import info.univpm.jobs.utils.StatsUtils;

/**
 * @author Montese SAra
 * @author Terrenzi Riccardo
 * Classe che genera un software demone che legge giornalmente
 * le offerte di lavoro pubblicate sull'Api e aggiorna il file di testo
 *
 */
@Component
public class Scheduler {
	//@Scheduled(fixedDelayString = "PT2M") // executes every 15 minutes
	@Scheduled(cron="0 0 12 * * *") //   executes at 12 o'clock of every day.
	public void aggiornamentoArchivio() throws NoJobFoundException {
		String nomeFile="./archivio.txt";
		 Archive a = new Archive();
		 a.setArchivio(StatsUtils.leggiFile(nomeFile));
		 for (Map.Entry<String, Vector<JSONArray>> entry : a.getArchivio().entrySet()) { 
			 System.out.println(entry.getKey() + " = " + entry.getValue().size());
			 System.out.println();
		}
		 StatsUtils.scriviFile(nomeFile,StatsUtils.fillHashMap( a.getArchivio()));
		
		/*for (Map.Entry<String, Vector<JSONArray>> entry : a.getArchivio().entrySet()) { 
			 System.out.println(entry.getKey() + " = " + entry.getValue().size());
			 System.out.println();
		}*/
	}
	
}
