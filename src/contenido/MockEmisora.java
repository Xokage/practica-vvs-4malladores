package contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase MockEmisora.
 */
public class MockEmisora implements Contenido {

	/** Título. */
	private String titulo;

	/** Duración. */
	private Integer duracion;

	/** Lista de reproducción de contenido. */
	private List<Contenido> listaReproduccion;

	/**
	 * @see contenido.Contenido#obtenerTitulo()
	 */
	@Override
	public String obtenerTitulo() {

		return titulo;

	}

	/**
	 * @see contenido.Contenido#obtenerDuracion()
	 */
	@Override
	public Integer obtenerDuracion() {

		return duracion;

	}

	/**
	 * @see contenido.Contenido#obtenerListaReproduccion()
	 */
	@Override
	public List<Contenido> obtenerListaReproduccion() {

		return listaReproduccion;

	}

	/**
	 * @see contenido.Contenido#buscar(java.lang.String)
	 */
	@Override
	public List<Contenido> buscar(String subcadena) {

		List<Contenido> lista = new ArrayList<Contenido>();

		return lista;
	}

	/**
	 * @see contenido.Contenido#agregar(contenido.Contenido,
	 *      contenido.Contenido)
	 */
	@Override
	public void agregar(Contenido contenido, Contenido predecesor) {

	}

	/**
	 * @see contenido.Contenido#eliminar(contenido.Contenido)
	 */
	@Override
	public void eliminar(Contenido contenido) {

	}

}
