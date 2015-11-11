package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import contenido.Emisora;

/**
 * Tests de la clase Emisora.
 */
public class EmisoraTest {
	
	/** La emisora. */
	Emisora emisora = new Emisora("La mejor del mundo",9);

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloTest() {
		assertTrue(emisora.obtenerTitulo().equals("La mejor del mundo"));
	}
	
	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionTest() {
		assertTrue(Integer.compare(emisora.obtenerDuracion(),9)==0);
	}
	
	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionTest() {
		Cancion cancion = new Cancion ("Mi gran noche",3);
		emisora.agregar(cancion, emisora);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		listaReproduccionIdeal.add(cancion);
		assertTrue(emisora.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Buscar test.
	 */
	@Test
	public void buscarTest() {
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		assertTrue(emisora.buscar("mejor").equals(listaReproduccionIdeal));
		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(emisora.buscar("otra emisora").equals(listaReproduccionIdeal));
	}
	
	/**
	 * Agregar test.
	 */
	@Test
	public void agregarTest() {
		Cancion cancion = new Cancion ("Mi gran noche",3);
		Anuncio anuncio = new Anuncio();
		emisora.agregar(cancion, emisora);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		listaReproduccionIdeal.add(cancion);
		assertTrue(emisora.obtenerListaReproduccion().equals(listaReproduccionIdeal));
		emisora.agregar(anuncio,emisora);
		listaReproduccionIdeal.add(1,anuncio);
		assertTrue(emisora.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
	
	/**
	 * Eliminar test.
	 */
	@Test
	public void eliminarTest() {
		Cancion cancion = new Cancion ("Mi gran noche",3);
		emisora.agregar(cancion, emisora);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		listaReproduccionIdeal.add(cancion);
		assertTrue(emisora.obtenerListaReproduccion().equals(listaReproduccionIdeal));
		emisora.eliminar(cancion);
		listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		assertTrue(emisora.obtenerListaReproduccion().equals(listaReproduccionIdeal));
	}
}
