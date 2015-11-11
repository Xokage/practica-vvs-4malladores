package servidor;

import java.util.List;

import contenido.Contenido;



/**
 * Interfaz Servidor.
 * El token caduca tras devolver 10 contenidos.
 * 	Se piden dos implementaciones: en una de ellas cada servidor tiene un Servidor de respaldo a quién pregunta en caso de que una búsqueda en local le resulte vacía.
 *
 */

public interface Servidor {
	//
	/**
	 * Obtener nombre.
	 *
	 * @return nombre del servidor.
	 */
	public String obtenerNombre();
	
	/**
	 * Da de alta un nuevo token.
	 *
	 * @return un token válido durante 10 peticiones.
	 */
	public String alta();
	
	/**
	 * Baja.
	 *
	 * @param token el token a eliminar
	 */
	public void baja(String token);
	
	/**
	 * Agrega contenido al servidor. Es necesario especificar el token de 
	 * modificación de contenido. 
	 *
	 * @param contenido contenido a agregar.
	 * @param token token de contenido.
	 */
	public void agregar(Contenido contenido, String token);
	
	/**
	 * Elimina contenido del servidor. Es necesario especificar el token de 
	 * modificación de contenido. 
	 *
	 * @param contenido contenido a agregar.
	 * @param token token de contenido.
	 */
	public void eliminar(Contenido contenido, String token);
	
	/**
	 * Busca contenido en el servidor.
	 * Si se usa un token vacío en las búsquedas, 
	 * la lista de contenidos empieza con y tiene un anuncio cada tres contenidos.
	 * @param subcadena subcadena por la que buscas el contenido.
	 * @param token token para la búsqueda.
	 * @return lista con el contenido del servidor.
	 */
	public List<Contenido> buscar(String subcadena, String token);
}
