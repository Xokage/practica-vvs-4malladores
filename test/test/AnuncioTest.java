package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Anuncio;
import contenido.Contenido;


/**
 * Tests de la clase Anuncio.
 */
public class AnuncioTest {
	
	/** Creamos un anuncio para probar. */
	Anuncio anuncio = new Anuncio();

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloTest() {
		assertTrue(anuncio.obtenerTitulo().equals("PUBLICIDAD"));
	}
	
	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(anuncio.obtenerDuracion(),5)==0);
	}
	
	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncio); 
		assertTrue(anuncio.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Buscar test.
	 */
	@Test
	public void buscarTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncio);
		assertTrue(anuncio.buscar("PUBLICIDAD").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(anuncio.buscar("OTRA COSA").equals(listaReproduccionIdeal));
	}
}
