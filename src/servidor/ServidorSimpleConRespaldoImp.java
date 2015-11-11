package servidor;

import java.util.List;

import contenido.Contenido;

/**
 * Clase ServidorSimpleConRespaldoImp.
 */
public class ServidorSimpleConRespaldoImp extends ServidorSimpleImp {

	/** El servidor de respaldo. */
	private Servidor servidorRespaldo;
	
	/**
	 * Instancia un nuevo servidor simple con respaldo.
	 *
	 * @param nombre el nombre del servidor.
	 * @param contenidoList la lista con el contenido del servidor.
	 * @param tokenContenido el token para modificar contenido.
	 * @param tokenValido token para realizar búsquedas.
	 * @param servidorRespaldo servidor de respaldo.
	 * @throws BackupServerException Si el servidor de backup es nulo lanza una excepción.
	 */
	public ServidorSimpleConRespaldoImp(String nombre, List<Contenido> contenidoList,
			String tokenContenido, String tokenValido, Servidor servidorRespaldo) throws BackupServerException{
		super(nombre,contenidoList,tokenContenido,tokenValido);
		if (servidorRespaldo == null) throw new BackupServerException();
		this.servidorRespaldo = servidorRespaldo;
	}

	/**
	 * @see servidor.ServidorSimpleImp#buscar(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Contenido> buscar(String subcadena, String token) {
		List<Contenido> resultado = super.buscar(subcadena,token);
		if(resultado.isEmpty()){
			return servidorRespaldo.buscar(subcadena,token);
		}
		else return resultado;
			
	}

}
