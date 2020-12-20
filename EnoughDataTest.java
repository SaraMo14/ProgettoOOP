package info.univpm.jobs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import info.univpm.jobs.exceptions.NotEnoughDataException;
import info.univpm.jobs.services.GetAndDecodeJob;

/**
 * Classe usata per testare l'eccezione nel caso in cui i dati su cui è richiesto fare le statistiche non sono sufficienti 
 * @author Montese Sara
 * @author Terrenzi Riccardo
 */
class EnoughDataTest {

	@Autowired
	GetAndDecodeJob getAndDecodeJob;

	private String statsParameter1;
	private String statsParameter2;
	
	@BeforeEach
	void setUp() throws Exception {
		getAndDecodeJob = new GetAndDecodeJob();
		statsParameter1 = "{\r\n"
				+ "    \"location\": \"all\",\r\n"
				+ "    \"description\": [\"Python\", \"java\"],\r\n"
				+ "    \"weeks\": \"500\"\r\n"
				+ "}";
		
		statsParameter2 = "{\r\n"
				+ "    \"location\": \"all\",\r\n"
				+ "    \"description\": [\"Python\", \"java\"],\r\n"
				+ "    \"weeks\": \"600\"\r\n"
				+ "}";
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test assert NotEnoughDataException")
	void test() {
		//Se il numero di settimane su cui fare la statistica è superiore al numero di settimane di cui si 
		//ha i dati salvati allora viene lanciata l'eccezione FilterIllegalArgumentException
		assertThrows(NotEnoughDataException.class, ()-> getAndDecodeJob.getStatsByBody(statsParameter1));	
	}
	@DisplayName("Test assert NotEnoughDataException")
	void test2() {
		//Se il numero di settimane su cui fare la statistica è superiore al numero di settimane di cui si 
		//ha i dati salvati allora viene lanciata l'eccezione FilterIllegalArgumentException
		assertThrows(NotEnoughDataException.class, ()-> getAndDecodeJob.getStatsByBody(statsParameter2));	
	}
}