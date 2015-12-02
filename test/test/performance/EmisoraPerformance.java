package test.performance;

import java.util.ArrayList;
import java.util.List;

import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import util.Pair;
import contenido.Cancion;
import contenido.Contenido;
import contenido.Emisora;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * Tests de la clase Emisora.
 */
public class EmisoraPerformance {

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen = 
			new ContenidoDuracionGenerator();

	/** Number of iteratios to check performance. */
	private final Integer itNumber = 10000;

	/** JETM Monitor to check performance. */
	private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	/**
	 * Obtener titulo test.
	 */
	public final void obtenerTituloPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = cNameGen.next();
			Emisora emisora = new Emisora(nombre);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraPerformance:obtenerTitulo");

		for (Emisora e : testElements) {
			e.obtenerTitulo();
		}

		point.collect();
	}

	/**
	 * Obtener duracion test.
	 */
	public final void obtenerDuracionPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = cNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			emisora.agregar(cancion, emisora);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraPerformance:obtenerDuracion");

		for (Emisora e : testElements) {
			e.obtenerDuracion();
		}

		point.collect();
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	public final void obtenerListaReproduccionPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = cNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			emisora.agregar(cancion, emisora);
			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraPerformance:obtenerListaReproduccion");

		for (Emisora e : testElements) {
			e.obtenerListaReproduccion();
		}

		point.collect();
	}

	/**
	 * Buscar test.
	 */
	public final void buscarPerformanceTest() {
		List<Pair<Emisora, String>> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
			listaReproduccionIdeal.add(emisora);

			testElements.add(new Pair<>(emisora, nombreEmisora));
		}

		EtmPoint point = etmMonitor.createPoint("EmisoraPerformance:buscar");

		for (Pair<Emisora, String> pair : testElements) {
			pair.getLeft().buscar(pair.getRight());
		}

		point.collect();

	}

	/**
	 * Agregar test.
	 */
	public final void agregarPerformanceTest() {
		List<Pair<Emisora, Cancion>> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = cNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			testElements.add(new Pair<>(emisora, cancion));
		}

		EtmPoint point = etmMonitor.createPoint("EmisoraPerformance:agregar");

		for (Pair<Emisora, Cancion> pair : testElements) {
			pair.getLeft().agregar(pair.getRight(), pair.getLeft());
		}

		point.collect();
	}

	/**
	 * Eliminar test.
	 */
	public final void eliminarPerformanceTest() {
		List<Pair<Emisora, Cancion>> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = cNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);
			emisora.agregar(cancion, emisora);

			testElements.add(new Pair<>(emisora, cancion));
		}

		EtmPoint point = etmMonitor.createPoint("EmisoraPerformance:eliminar");

		for (Pair<Emisora, Cancion> pair : testElements) {
			pair.getLeft().eliminar(pair.getRight());
		}

		point.collect();

	}
}
