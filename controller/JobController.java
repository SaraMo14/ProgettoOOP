package info.univpm.jobs.controller;import info.univpm.jobs.services.GetAndDecodeJob;
import info.univpm.jobs.statistics.Archive;
import info.univpm.jobs.statistics.StatsSingleLocation;
import info.univpm.jobs.utils.ParsingJson;
import info.univpm.jobs.utils.RecognizeFilter;
import info.univpm.jobs.utils.StatsUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import info.univpm.jobs.exceptions.FilterIllegalArgumentException;
import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.exceptions.NotEnoughDataException;
import info.univpm.jobs.filters.Filter;
import info.univpm.jobs.model.Job;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Rappresenta la classe che gestisce tutte le chiamate al Server 
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */
@RestController
public class JobController {

	//private static String url = "https://jobs.github.com/positions?search=part-time";
	private static String url1 = "https://jobs.github.com/positions";
	
	@Autowired
	GetAndDecodeJob getAndDecodeJob;
	
	//superfluo
	/*@PostMapping("/Data/loc")
	public ResponseEntity<Object>  getDataLocbody(@RequestBody JSONObject location ) {
		try{
			return new ResponseEntity<>(getAndDecodeJob.getJobsByLocbody(location), HttpStatus.OK) ;
		}
		catch(NoJobFoundException e) {
			System.out.println(e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
		catch (Exception e) {
			throw new RuntimeException("Failed", e);
		}
	}
	
	@GetMapping("/Data")
	public ResponseEntity<Object>  getDataLocDes(@RequestParam(name = "location", required=false) String location,
							@RequestParam(name = "description", required=false) String description){
		
		try{
			if(location ==  null && description != null) {
			return new ResponseEntity<>(getAndDecodeJob.getJobsByDes(description), HttpStatus.OK);
			}
			else if(location !=  null && description == null) {
				return new ResponseEntity<>(getAndDecodeJob.getJobsByLoc(location), HttpStatus.OK);
			}
			else if(location !=  null && description != null) {
				return new ResponseEntity<>(getAndDecodeJob.getJobsByDesAndLoc(description, location), HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(getAndDecodeJob.getJobs(url1), HttpStatus.OK);
		}
		catch(NoJobFoundException e) {
			System.out.println(e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
		catch (Exception e) {
			throw new RuntimeException("Failed", e);
		}
	}*/
	
	@GetMapping("/Data/{id}")
	public ResponseEntity<Object> getId(@PathVariable("id") String id ) {
		try{
			return  new ResponseEntity<>(getAndDecodeJob.getJobById(id), HttpStatus.OK);
		}
		catch(NoJobFoundException e) {
			System.out.println(e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
		catch (Exception e) {
			throw new RuntimeException("Failed", e);
		}
	}
	
	@GetMapping("/allData")
	public  ResponseEntity<Object> getAllData() {
		try {
			return new  ResponseEntity<>(getAndDecodeJob.getAllPages(), HttpStatus.OK);
		}
		catch(NoJobFoundException | ParseException e) {
			System.out.println(e);
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
	}
	
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
				return new ResponseEntity<>(getAndDecodeJob.getJobByBody(Filter.JsonBodyElaborationLoc(finalMap)), HttpStatus.OK);
					
			} 
			catch(NoJobFoundException e) {
				System.out.println(e);
				//"La richiesta non mostra risultati. Provare una nuova richiesta."
				return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
			}
			catch(FilterIllegalArgumentException e ) {
				System.out.println(e);
				return new ResponseEntity<>("Il filtro inserito è errato", HttpStatus.BAD_REQUEST);
			} catch (ParseException e) {
				throw new RuntimeException("Failed", e);

			}
	}	
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
	//metodo per visualizzare citta
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
