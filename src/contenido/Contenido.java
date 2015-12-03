package contenido;

import java.util.List;

/**
 * Interfaz Contenido. Se piden tres implementaciones: una para canciones, otra
 * para anuncios, y otra para emisoras. Los anuncios duran siempre 5 segundos.
 * Los anuncions tienen siempre como título 'PUBLICIDAD'.
 */
public interface Contenido {
	

	
	/**
	 * Obtener titulo.
	 *
	 * @return título del contenido.
	 */
	String obtenerTitulo();
	
	/**
	 * Obtener duración.
	 *
	 * @return segundos que dura el contenido.
	 */
	Integer obtenerDuracion();
	
	/**
	 * Obtener lista reproducción.
	 *
	 * @return la lista de reproducción.
	 */
	List<Contenido> obtenerListaReproduccion();
	
	/**
	 * Busca contenido que contenga en su título una subcadena.
	 *
	 * @param subcadena lo que se va a buscar en el título.
	 * @return lista con el contenido buscado.
	 */
	List<Contenido> buscar(String subcadena);
	
	/**
	 * Agrega contenido.
	 * En canciones y anuncios agregar no tiene efecto.
	 * @param contenido el contenido a agregar.
	 * @param predecesor el contenido anterior.
	 */
	void agregar(Contenido contenido, Contenido predecesor);
	
	/**
	 * Elimina contenido.
	 * En canciones y anuncios eliminar no tiene efecto
	 * @param contenido el contenido a eliminar.
	 */

	void eliminar(Contenido contenido);
}
