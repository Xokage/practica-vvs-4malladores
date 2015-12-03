package contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Emisora.
 */
public class Emisora implements Contenido {

	/** Título. */
	private String titulo;

	/** Duración. */
	private Integer duracion;

	/** Lista de reproducción de contenido. */
	private List<Contenido> listaReproduccion;

	/**
	 * Instancia una nueva emisora.
	 *
	 * @param tituloRecibido
	 *            título de la emisora.
	 */
	public Emisora(final String tituloRecibido) {
		this.titulo = tituloRecibido;
		this.duracion = 0;
		this.listaReproduccion = new ArrayList<Contenido>();
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

		List<Contenido> lista = new ArrayList<Contenido>();

		for (Contenido c : listaReproduccion) {

			if (c.obtenerTitulo().contains(subcadena)) {

				lista.add(c);
			}

		}

		return lista;
	}

	/**
	 * @see contenido.Contenido#agregar(contenido.Contenido,
	 *      contenido.Contenido)
	 */
	@Override
	public final void agregar(final Contenido contenido,
			final Contenido predecesor) {

		Integer indice = listaReproduccion.indexOf(predecesor);
		listaReproduccion.add(indice + 1, contenido);
		this.duracion += contenido.obtenerDuracion();

	}

	/**
	 * @see contenido.Contenido#eliminar(contenido.Contenido)
	 */
	@Override
	public final void eliminar(final Contenido contenido) {

		listaReproduccion.remove(contenido);
		this.duracion -= contenido.obtenerDuracion();

	}

}
