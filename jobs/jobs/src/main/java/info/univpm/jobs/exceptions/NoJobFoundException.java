package info.univpm.jobs.exceptions;
/**
 * Classe di eccezione personalizzata che viene lanciata se l'array delle offerte di lavoro e' vuoto o e' null.
 * @author Montese Sara
 * @author Terrenzi Riccardo
 *
 */
public class NoJobFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
