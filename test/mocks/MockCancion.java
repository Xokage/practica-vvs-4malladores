package mocks;

import java.util.ArrayList;
import java.util.List;

import contenido.Contenido;

/**
 * Clase MockCancion.
 */
public class MockCancion implements Contenido {
	
	/** Título. */
	private String titulo;
	
	/** Duración. */
	private int duracion;
	
	/** Lista de Reproduccion del Contenido. */
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
			return listaReproduccion;
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
