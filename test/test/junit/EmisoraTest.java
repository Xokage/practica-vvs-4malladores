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
	private final GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen =
			new ContenidoDuracionGenerator();

	/** La emisora. */

	/**
	 * Obtener titulo test.
	 */
	@Test
	public final void obtenerTituloTest() {
		String nombre = cNameGen.next();
		Emisora emisora = new Emisora(nombre);

		assertTrue(emisora.obtenerTitulo().equals(nombre));
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public final void obtenerDuracionTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, emisora);
		assertTrue(Integer.compare(emisora.obtenerDuracion(),
				duracionCancion) == 0);
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public final void obtenerListaReproduccionTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, null);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}

	/**
	 * Buscar test.
	 */
	@Test
	public final void buscarTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, null);
		
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);
		assertTrue(emisora.buscar(nombreCancion)
				.equals(listaReproduccionIdeal));

		listaReproduccionIdeal = new ArrayList<Contenido>();
		assertTrue(emisora.buscar(cNameGen.next()).equals(
				listaReproduccionIdeal));
	}

	/**
	 * Agregar test.
	 */
	@Test
	public final void agregarTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		Anuncio anuncio = new Anuncio();

		emisora.agregar(cancion, null);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));

		emisora.agregar(anuncio, cancion);
		listaReproduccionIdeal.add(1, anuncio);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}

	/**
	 * Eliminar test.
	 */
	@Test
	public final void eliminarTest() {
		String nombreEmisora = cNameGen.next();
		Emisora emisora = new Emisora(nombreEmisora);

		String nombreCancion = cNameGen.next();
		Integer duracionCancion = cDuracionGen.next();
		Cancion cancion = new Cancion(nombreCancion, duracionCancion);

		emisora.agregar(cancion, null);
		List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
		listaReproduccionIdeal.add(cancion);

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));

		emisora.eliminar(cancion);
		listaReproduccionIdeal = new ArrayList<Contenido>();

		assertTrue(emisora.obtenerListaReproduccion().equals(
				listaReproduccionIdeal));
	}
}
