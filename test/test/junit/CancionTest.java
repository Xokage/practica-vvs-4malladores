package test.junit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import contenido.Cancion;
import contenido.Contenido;

/**
 * Tests para la clase Cancion.
 */
public class CancionTest {

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen =
			new ContenidoDuracionGenerator();

	/**
	 * Obtener titulo test.
	 */
	@Test
	public final void obtenerTituloTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		assertTrue(cancion.obtenerTitulo().equals(nombre));
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public final void obtenerDuracionTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		assertTrue(Integer.compare(cancion.obtenerDuracion(), duracion) == 0);
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public final void obtenerListaReproduccionTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}

	/**
	 * Buscar test.
	 */
	@Test
	public final void buscarTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.buscar(nombre).equals(listaReproduccionIdeal));

		List<Contenido> listaReproduccionVacia = new ArrayList<Contenido>();
		assertTrue(cancion.buscar(nombre + cNameGen.next()).equals(
				listaReproduccionVacia));

	}

}
