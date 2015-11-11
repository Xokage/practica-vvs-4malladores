package servidor;

/**
 * Error BackupServerException.
 */
public class BackupServerException extends Exception {

	/** Constante serialVersionUID. Se utiliza para envitar problemas
	 * de serialización.
	 * 
	 *  Para más información:
	 *  @see <a href="URL#value">https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it</a>
	 *
	 */
	
	private static final long serialVersionUID = -5235371235063229722L;

	/**
	 * Instancia una nueva backup server exception.
	 */
	public BackupServerException(){
		System.err.println("Backup server is null");
	}
	
}
