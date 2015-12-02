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
	GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	ContenidoDuracionGenerator cDuracionGen = new ContenidoDuracionGenerator();

	/** Number of iteratios to check performance */
	private final Integer IT_NUMBER = 10000;

	/** JETM Monitor to check performance */
	private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	/**
	 * Obtener titulo test.
	 */
	public void obtenerTituloPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
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
	public void obtenerDuracionPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
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
	public void obtenerListaReproduccionPerformanceTest() {
		List<Emisora> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
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
	public void buscarPerformanceTest() {
		List<Pair<Emisora, String>> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			List<Contenido> listaReproduccionIdeal = new ArrayList<Contenido>();
			listaReproduccionIdeal.add(emisora);

			testElements.add(new Pair<>(emisora, nombreEmisora));
		}

		EtmPoint point = etmMonitor.createPoint("CancionPerformance:buscar");

		for (Pair<Emisora, String> pair : testElements) {
			pair.getLeft().buscar(pair.getRight());
		}

		point.collect();

	}

	/**
	 * Agregar test.
	 */
	public void agregarPerformanceTest() {
		List<Pair<Emisora, Cancion>> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = cNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			testElements.add(new Pair<>(emisora, cancion));
		}

		EtmPoint point = etmMonitor.createPoint("CancionPerformance:agregar");

		for (Pair<Emisora, Cancion> pair : testElements) {
			pair.getLeft().agregar(pair.getRight(), pair.getLeft());
		}

		point.collect();
	}

	/**
	 * Eliminar test.
	 */
	public void eliminarPerformanceTest() {
		List<Pair<Emisora, Cancion>> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombreEmisora = cNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = cNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);
			emisora.agregar(cancion, emisora);

			testElements.add(new Pair<>(emisora, cancion));
		}

		EtmPoint point = etmMonitor.createPoint("CancionPerformance:eliminar");

		for (Pair<Emisora, Cancion> pair : testElements) {
			pair.getLeft().eliminar(pair.getRight());
		}

		point.collect();

	}
}
