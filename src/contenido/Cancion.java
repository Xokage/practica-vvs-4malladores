package contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cancion.
 */
public class Cancion implements Contenido {

	/** Título. */
	private String titulo;

	/** Duración. */
	private int duracion;

	/** Lista de Reproduccion del Contenido. */
	private List<Contenido> listaReproduccion;

	/**
	 * Instancia una nueva canción.
	 *
	 * @param nombre
	 *            nombre de la canción
	 * @param duracionRecibida
	 *            duración de la canción (en segundos)
	 */
	public Cancion(final String nombre, final int duracionRecibida) {
		titulo = nombre;
		this.duracion = duracionRecibida;
		listaReproduccion = new ArrayList<Contenido>();
		listaReproduccion.add(this);
	}

	/**
	 * @see contenido.Contenido#obtenerTitulo()
	 */
	@Override
	public final String obtenerTitulo() {
		return titulo;
	}

	/**
	 * @see contenido.Contenido#obtenerDuracion()
	 */
	@Override
	public final Integer obtenerDuracion() {
		return duracion;
	}

	/**
	 * @see contenido.Contenido#obtenerListaReproduccion()
	 */
	@Override
	public final List<Contenido> obtenerListaReproduccion() {
		return listaReproduccion;
	}

	/**
	 * @see contenido.Contenido#buscar(java.lang.String)
	 */
	@Override
	public final List<Contenido> buscar(final String subcadena) {
		if (titulo.contains(subcadena)) {
			return listaReproduccion;
		} else {
			return new ArrayList<Contenido>();
		}
	}

	/**
	 * No realiza ninguna función.
	 */
	@Override
	public void agregar(final Contenido contenido, final Contenido predecesor) {

	}

	/**
	 * No realiza ninguna función.
	 */
	@Override
	public void eliminar(final Contenido contenido) {

	}

}
