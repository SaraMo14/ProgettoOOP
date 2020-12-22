package info.univpm.jobs.controller;

import info.univpm.jobs.services.GetAndDecodeJob;
import info.univpm.jobs.utils.ParsingJson;
import info.univpm.jobs.utils.RecognizeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import info.univpm.jobs.exceptions.FilterIllegalArgumentException;
import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.exceptions.NotEnoughDataException;
import info.univpm.jobs.filters.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * Rappresenta la classe che gestisce tutte le chiamate al Server 
 * @author Montese Sara
 * @author Terrenzi Ricc
 * ardo
 */
/**
 * @author saram
 *
 */
/**
 * @author saram
 *
 */
@RestController
public class JobController {

	@Autowired
	GetAndDecodeJob getAndDecodeJob;

	/** Rotta di tipo GET che ritorna le istruzioni per il corretto utilizzo del programma
	 * @return Vector di stringhe contenente le istruzioni lette dal file instructions.txt
	 */
	@GetMapping("/Instructions")
	public ResponseEntity<Object> getInfo(){
		return new ResponseEntity<>(getAndDecodeJob.getInstructions(), HttpStatus.OK);
	}

	/**Rotta di tipo GET che ritorna tutte le offerte di lavoro presenti su GitHub JObs
	 * @return JsonArray contenente le offerte di lavoro
	 */
	@GetMapping("/Data")
	public  ResponseEntity<Object> getAllData() {
		try {
			return new  ResponseEntity<>(getAndDecodeJob.getAllPages(), HttpStatus.OK);
		}
		catch(NoJobFoundException | ParseException e) {
			System.out.println(e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
	}


	/**Rotta di tipo POST che effettua il filtraggio delle offerte di lavoro in base a location, description, title e company,
	 * inseriti dall'utente.
	 * Le parole chiave vengono prelevate dal body della richiesta.
	 * @param param Body della richiesta POST contenente le parole chiavi.
	 * @return JSONArray con le offerte che soddisfano la richiesta.
	 */
	@PostMapping(value="/Filter")
	public ResponseEntity<Object>  userFilteredData(@RequestBody String param) {
		try {
				JSONObject jsonString = (JSONObject) JSONValue.parseWithException(param);
				Map<String, Object> myMap = ParsingJson.JSONStringToMap(jsonString.toJSONString());
				HashMap<String,ArrayList<String>> finalMap = new HashMap<String,ArrayList<String>>();
				for (Map.Entry<String, Object> entry : myMap.entrySet()) {
				   finalMap.putAll(RecognizeFilter.parseFilterOperator(entry.getKey(), entry.getValue()));
				  }
				//rivedi ciclo
				JSONArray finalArray = new JSONArray();
				if((jsonString.get("title")!=null) && (jsonString.get("company")!=null)) {
					finalArray = getAndDecodeJob.getJobByTitleAndCompany(getAndDecodeJob.getJobByBody(Filter.JsonBodyElaborationLoc(finalMap)), (String) jsonString.get("title"), "title");
					finalArray = getAndDecodeJob.getJobByTitleAndCompany(finalArray, (String) jsonString.get("company"), "company");
				}else if((jsonString.get("title")!=null)&&(jsonString.get("company")==null)) {
					finalArray = getAndDecodeJob.getJobByTitleAndCompany(getAndDecodeJob.getJobByBody(Filter.JsonBodyElaborationLoc(finalMap)), (String) jsonString.get("title"), "title");
				}else if((jsonString.get("title")==null)&&(jsonString.get("company")!=null)) {
					finalArray = getAndDecodeJob.getJobByTitleAndCompany(getAndDecodeJob.getJobByBody(Filter.JsonBodyElaborationLoc(finalMap)), (String) jsonString.get("company"), "company");
					
				}else 
					finalArray= getAndDecodeJob.getJobByBody(Filter.JsonBodyElaborationLoc(finalMap));
				return new ResponseEntity<>(finalArray, HttpStatus.OK);
				
					
			} 
			catch(NoJobFoundException e) {
				System.out.println(e);
				return new ResponseEntity<>("La richiesta non mostra risultati. Provare una nuova richiesta.", HttpStatus.BAD_REQUEST);
			}
			catch(FilterIllegalArgumentException e ) {
				System.out.println(e);
				return new ResponseEntity<>("Il filtro inserito è errato", HttpStatus.BAD_REQUEST);
			} catch (ParseException e) {
				throw new RuntimeException("Failed", e);

			}
	}	
	
	/**
	 * Rotta di tipo GET che effettua il calcolo delle statistiche.
	 * Le statistiche vengono effettuate sia in base alla description che in base alla location.
	 * L'utente deve inoltre specificare il numero di mesi o settimane dalla attuale rispetto a cui calcolare le statistiche
	 * @param Tipo Parametro che identifica il tipo di statistiche ove deve essere effettuata (su i Tweet Italiani o Tedeschi).
	 * @return Ritornano le statistiche effettuate.
	 */
	@PostMapping("/Stats")
	public ResponseEntity<Object> getStats(@RequestBody String param) throws NoJobFoundException, ParseException{
		try {
			return new ResponseEntity<>(getAndDecodeJob.getStatsByBody(param),HttpStatus.OK);
			
		} catch (NotEnoughDataException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Non ci sono dati sufficienti per effettuare la statistica",HttpStatus.INSUFFICIENT_STORAGE);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed", e);
		}
		
	}
	/**Rotta di tipo GET per visualizzare la lista dei Paesi su cui è possibile richiedere le statistiche
	 * @return JSONArray contenente la lista dei Paesi
	 */
	@GetMapping("/Countries")
	public ResponseEntity<Object> getLocations(){
		try {
			return new ResponseEntity<>(getAndDecodeJob.getCountryList(), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
