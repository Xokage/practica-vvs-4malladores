package test.junit;

import static org.junit.Assert.*;

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
	GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	ContenidoDuracionGenerator cDuracionGen = new ContenidoDuracionGenerator();

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		assertTrue(cancion.obtenerTitulo().equals(nombre));
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		assertTrue(Integer.compare(cancion.obtenerDuracion(), duracion) == 0);
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionTest() {
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
	public void buscarTest() {
		String nombre = cNameGen.next();
		Integer duracion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombre, duracion);

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(cancion.buscar(nombre).equals(listaReproduccionIdeal));

		List<Contenido> listaReproduccionVacia = new ArrayList<Contenido>();
		assertTrue(cancion.buscar(cNameGen.next()).equals(
				listaReproduccionVacia));

	}

}
