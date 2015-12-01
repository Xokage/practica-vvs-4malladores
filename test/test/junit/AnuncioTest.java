package test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.generators.GeneralNameGenerator;
import contenido.Anuncio;
import contenido.Contenido;

/**
 * Tests de la clase Anuncio.
 */
public class AnuncioTest {

	/** Generador de nombres válidos. */
	GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloTest() {
		Anuncio anuncio = new Anuncio();

		assertTrue(anuncio.obtenerTitulo().equals("PUBLICIDAD"));
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionTest() {
		Anuncio anuncio = new Anuncio();

		assertTrue(Integer.compare(anuncio.obtenerDuracion(), 5) == 0);
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionTest() {
		Anuncio anuncio = new Anuncio();

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncio);
		assertTrue(anuncio.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}

	/**
	 * Buscar test.
	 */
	@Test
	public void buscarTest() {
		Anuncio anuncio = new Anuncio();

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncio);
		assertTrue(anuncio.buscar("PUBLICIDAD").equals(listaReproduccionIdeal));
		List<Contenido> listaReproduccionVacia = new ArrayList<Contenido>();
		assertTrue(anuncio.buscar(cNameGen.next()).equals(
				listaReproduccionVacia));

	}
}
