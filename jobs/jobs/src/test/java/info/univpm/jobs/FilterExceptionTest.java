package info.univpm.jobs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.univpm.jobs.exceptions.FilterIllegalArgumentException;
import info.univpm.jobs.filters.Filter;
import info.univpm.jobs.utils.ParsingJson;
import info.univpm.jobs.utils.RecognizeFilter;

/**
 * Classe usata per testare l'eccezione nel caso in cui l'operatore di filtro inserito per la richiesta è errato
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */
class FilterExceptionTest {
	@Autowired
	Filter filter;
	private String param;
	private HashMap<String,ArrayList<String>> finalMap;
	
	@BeforeEach
	void setUp() throws Exception {	
		param = "{\r\n"
				+ "    \"location\": {\"$\":[\"Berlin\", \"new york\", \"amsterdam\"]},\r\n"
				+ "    \"description\": \"java\",\r\n"
				+ "    \"company\": \"\",\r\n"
				+ "    \"title\": \"data analyst\"\r\n"
				+ "}";
		JSONObject jsonString = (JSONObject) JSONValue.parseWithException(param);
		Map<String, Object> myMap = ParsingJson.JSONStringToMap(jsonString.toJSONString());
		finalMap = new HashMap<String,ArrayList<String>>();
		for (Map.Entry<String, Object> entry : myMap.entrySet()) {
			finalMap.putAll(RecognizeFilter.parseFilterOperator(entry.getKey(), entry.getValue()));
	  
		}

	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	@DisplayName("Test assert FilterIllegalArgumentException")
	void test() {
		//Se l'operatore di filtraggio inserito è diverso da quelli previsti 
		// allora viene lanciata l'eccezione FilterIllegalArgumentException
		assertThrows(FilterIllegalArgumentException.class, ()-> Filter.JsonBodyElaborationLoc(finalMap));	
		}
}