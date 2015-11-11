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
	 * @param nombre nombre de la canción
	 * @param duracion duración de la canción (en segundos)
	 */
	public Cancion(String nombre, int duracion){
		titulo = nombre;
		this.duracion = duracion;
		listaReproduccion = new ArrayList<Contenido>();
		listaReproduccion.add(this);
	}
	
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
		if (titulo.equalsIgnoreCase(subcadena))
			return listaReproduccion;
		else return new ArrayList<Contenido>();
	}

	/**
	 * No realiza ninguna función.
	 */
	@Override
	public void agregar(Contenido contenido, Contenido predecesor) {
		
	}

	/**
	 * No realiza ninguna función.
	 */
	@Override
	public void eliminar(Contenido contenido) {
		
	}

}
