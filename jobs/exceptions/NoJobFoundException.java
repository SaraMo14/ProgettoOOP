package info.univpm.jobs.exceptions;
/**
 * Classe di eccezione personalizzata per l'array di lavori, se e' vuoto o e' null.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 *
 */
public class NoJobFoundException extends Exception{
	/**
	 * Costruttore della classe di eccezione personalizzata.
	 */
	public NoJobFoundException() {
		super();
	}
	/**
	 * Costruttore con messaggio di errore della classe di eccezione personalizzata.
	 */
	public NoJobFoundException(String msg) {
		super(msg);
	}
}
