package info.univpm.jobs.exceptions;

public class FilterIllegalArgumentException extends Exception {
	/**
	 * Classe di eccezione personalizzata. Essa segnala che il filtro inserito dall'utente nel jsonBody della richiesta 
	 * è errato
	 * @author Montese Sara
	 * @author Terrenzi Riccardo
	 */
	private static final long serialVersionUID = 1L;
	public FilterIllegalArgumentException() {
		super();
	}
	public FilterIllegalArgumentException(String msg) {
		super(msg);
	}
}
