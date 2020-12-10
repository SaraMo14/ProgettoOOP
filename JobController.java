package info.univpm.jobs.controller;
import info.univpm.jobs.services.GetAndDecodeJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.model.Job;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	private static String url = "https://jobs.github.com/positions?search=part-time";
	private static String url1 = "https://jobs.github.com/positions";
	/*@AutoWired
	 * private GetAndDecodeJob getAndDecodeJob;
	 */
	//da sistemare poichè prende una sola pagina
	/*@GetMapping("/Data")
	public JSONArray getData() throws NoJobFoundException {
		return GetAndDecodeJob.getJobs("https://jobs.github.com/positions.json");
		}*/
	
	/*@GetMapping("/Data")
	public JSONArray getDataPartTime() throws NoJobFoundException {
		return  GetAndDecodeJob.getJobs(url);
	}*/
	
	/*@PostMapping()
	public JSONArray getFilteredDataPartTime(@RequestParam(name = "location") String Tipo) throws NoJobFoundException{
		JSONArray arrayJobs = new JSONArray();
		arrayJobs = GetAndDecodeJob.getJobs(url);
		
		return arrayJobs;
	}*/
	
	/*@GetMapping("/Data/{location}")
	public JSONArray getDataLoc(@PathVariable("location") String location) throws NoJobFoundException {
		return  GetAndDecodeJob.getJobsByLoc( location);
	}*/
	
	@GetMapping("/Data")
	public JSONArray getDataLoc(@RequestParam(name = "location", required=false) String location,
							@RequestParam(name = "description", required=false) String description) throws NoJobFoundException {
		
		if(location ==  null && description != null) {
			
			return GetAndDecodeJob.getJobsByDes(description);
			
		}
		else if(location !=  null && description == null) {
			return GetAndDecodeJob.getJobsByLoc(location);
		}
		else if(location !=  null && description != null) {
			return GetAndDecodeJob.getJobsByDesAndLoc(description, location);
		}
		else{
			return GetAndDecodeJob.getJobs(url1);
		}
		
		
		
	}
	
	/*@GetMapping("/Data/{description}/{location}")
	public JSONArray getDataLocDes(@PathVariable("location") String location, @PathVariable("description") String description ) throws NoJobFoundException {
		return  GetAndDecodeJob.getJobsByDesAndLoc( description, location);
	}
	
	@GetMapping("/Data/{decription}")
	public JSONArray getDataDes(@PathVariable("description") String description) throws NoJobFoundException{
		return GetAndDecodeJob.getJobsByDes( description);
	}*/
	
	}
