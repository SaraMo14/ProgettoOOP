package info.univpm.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
* Classe che si occupa di gestire ed avviare tutti i componenti dell'applicazione.
* @author Montese Sara
* @author Terrenzi Riccardo
*/

@SpringBootApplication
public class JobsApplication {

	/**
	 * Metodo main che inizializza i componenti e fa partire l'applicazione SpringBoot.
	 * @param args Possibile argomento passato dal chiamante. 
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(JobsApplication.class, args);
	}

}
