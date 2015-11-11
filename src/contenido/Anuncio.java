package contenido;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Anuncio.
 *
 * @author Cristian Canosa, Martín Quinteiro
 */
public class Anuncio implements Contenido {
	
	/** Título. En un anuncio su título es siempre "PUBLICIDAD"*/
	private String titulo;
	
	/** Duración. Los anuncios duran siempre 5 segundos. */
	private int duracion;
	
	/** Lista de Reproduccion de Contenido. */
	private List<Contenido> listaReproduccion;
	
	/**
	 * Instancia un nuevo anuncio que dura 5 segundos y de título 'PUBLICIDAD'.
	 */
	public Anuncio(){
		titulo = "PUBLICIDAD";
		duracion = 5;
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
