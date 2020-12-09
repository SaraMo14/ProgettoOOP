package info.univpm.jobs.controller;
import info.univpm.jobs.services.GetAndDecodeJob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.univpm.jobs.exceptions.NoJobFoundException;
import info.univpm.jobs.model.Job;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * Rappresenta la classe che gestisce tutte le chiamate al Server 
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */
@RestController
public class JobController {
	/*@AutoWired
	 * private GetAndDecodeJob getAndDecodeJob;
	 */
	@GetMapping("/Data")
	public JSONArray getData() throws NoJobFoundException {
		return GetAndDecodeJob.getJobs("https://jobs.github.com/positions.json");
		}
	}
