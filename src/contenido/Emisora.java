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
	 * @param titulo título de la emisora.
	 * @param duracion duración de la emisora (en segundos).
	 */
	public Emisora(String titulo) {
		this.titulo = titulo;
		this.duracion = 0;
		this.listaReproduccion = new ArrayList<Contenido>();
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
		
		List<Contenido> lista = new ArrayList<Contenido>();
		
		for (Contenido c : listaReproduccion) {
			
			if (c.obtenerTitulo().contains(subcadena)){
				
				lista.add(c);
			}
				
		}
		
		return lista;
	}

	/**
	 * @see contenido.Contenido#agregar(contenido.Contenido, contenido.Contenido)
	 */
	@Override
	public void agregar(Contenido contenido, Contenido predecesor) {
		
		Integer indice = listaReproduccion.indexOf(predecesor);
		listaReproduccion.add(indice + 1, contenido);
		this.duracion += contenido.obtenerDuracion();
		
	}

	/**
	 * @see contenido.Contenido#eliminar(contenido.Contenido)
	 */
	@Override
	public void eliminar(Contenido contenido) {
		
		listaReproduccion.remove(contenido);
		this.duracion -= contenido.obtenerDuracion();
		
	}


}
