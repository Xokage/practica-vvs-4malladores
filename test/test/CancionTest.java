package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Cancion;
import contenido.Contenido;

/**
 * Tests para la clase Cancion.
 */
public class CancionTest {
	
	/** La cancion. */
	Cancion cancion = new Cancion("Mi gran noche",3);

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloTest() {
		assertTrue(cancion.obtenerTitulo().equals("Mi gran noche"));
	}
	
	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(cancion.obtenerDuracion(),3)==0);
	}
	
	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Buscar test.
	 */
	@Test
	public void buscarTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.buscar("Mi gran noche").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(cancion.buscar("OTRA COSA").equals(listaReproduccionIdeal));
	}

}
