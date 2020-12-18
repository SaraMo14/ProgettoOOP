package info.univpm.jobs.exceptions;

public class NotEnoughDataException extends Exception{

	/**
	 * Classe di eccezione personalizzata. Essa segnala che il numero di settimane su cui l'utente vuole fare
	 * le statistiche sia superiore a quelle salvate nel nostro archivio
	 * @author Montese Sara
	 * @author Terrenzi Riccardo
	 */
	private static final long serialVersionUID = 1L;
	public NotEnoughDataException () {
		super();
	}
	public NotEnoughDataException (String msg) {
		super(msg);
	}
}
