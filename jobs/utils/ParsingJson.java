package info.univpm.jobs.utils;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.univpm.jobs.model.JobAdv;
/**
 * Classe per il parsing dell'arraList di tweet nel formato JSON.
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
}