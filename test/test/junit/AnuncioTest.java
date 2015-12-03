package test.junit;

import static org.junit.Assert.assertTrue;

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
	private final GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Constante do que dura un anuncio. */
	private final int duracionAnuncio = 5;

	/**
	 * Obtener titulo test.
	 */
	@Test
	public final void obtenerTituloTest() {
		Anuncio anuncio = new Anuncio();

		assertTrue(anuncio.obtenerTitulo().equals("PUBLICIDAD"));
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public final void obtenerDuracionTest() {
		Anuncio anuncio = new Anuncio();

		assertTrue(Integer.compare(anuncio.obtenerDuracion(),
				duracionAnuncio) == 0);
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public final void obtenerListaReproduccionTest() {
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
	public final void buscarTest() {
		Anuncio anuncio = new Anuncio();

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(anuncio);
		assertTrue(anuncio.buscar("PUBLICIDAD").equals(listaReproduccionIdeal));
		List<Contenido> listaReproduccionVacia = new ArrayList<Contenido>();
		assertTrue(anuncio.buscar(cNameGen.next()).equals(
				listaReproduccionVacia));

	}
}
