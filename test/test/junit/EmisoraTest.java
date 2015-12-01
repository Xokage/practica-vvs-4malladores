package test.junit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import contenido.Emisora;

/**
 * Tests de la clase Emisora.
 */
public class EmisoraTest {

	/** Generador de nombres válidos. */
	GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	ContenidoDuracionGenerator cDuracionGen = new ContenidoDuracionGenerator();

	/** La emisora. */

	/**
	 * Obtener titulo test.
	 */
	@Test
	public void obtenerTituloTest() {
		String nombre = cNameGen.next();
		Emisora emisora = new Emisora(nombre);

		assertTrue(emisora.obtenerTitulo().equals(nombre));
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, emisora);
		assertTrue(Integer.compare(emisora.obtenerDuracion(), duracionCancion) == 0);
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, emisora);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		listaReproduccionIdeal.add(cancion);
		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}

	/**
	 * Buscar test.
	 */
	@Test
	public void buscarTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		assertTrue(emisora.buscar(nombreEmisora).equals(listaReproduccionIdeal));

		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(emisora.buscar(cNameGen.next()).equals(
				listaReproduccionIdeal));
	}

	/**
	 * Agregar test.
	 */
	@Test
	public void agregarTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		Anuncio anuncio = new Anuncio();

		emisora.agregar(cancion, emisora);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		listaReproduccionIdeal.add(cancion);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));

		emisora.agregar(anuncio, emisora);
		listaReproduccionIdeal.add(1, anuncio);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}

	/**
	 * Eliminar test.
	 */
	@Test
	public void eliminarTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, emisora);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);
		listaReproduccionIdeal.add(cancion);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));

		emisora.eliminar(cancion);
		listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(emisora);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}
}
