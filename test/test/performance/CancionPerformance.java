package test.performance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import util.Pair;
import contenido.Cancion;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * Tests para la clase Cancion.
 */
public class CancionPerformance {

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
	@Test
	public void obtenerTituloPerformanceTest() {
		List<Cancion> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombre = cNameGen.next();
			Integer duracion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombre, duracion);

			testElements.add(cancion);
		}

		EtmPoint point = etmMonitor
				.createPoint("CancionPerformance:obtenerTitulo");

		for (Cancion c : testElements) {
			c.obtenerTitulo();
		}

		point.collect();
	}

	/**
	 * Obtener duracion test.
	 */
	@Test
	public void obtenerDuracionPerformanceTest() {
		List<Cancion> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombre = cNameGen.next();
			Integer duracion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombre, duracion);

			testElements.add(cancion);
		}

		EtmPoint point = etmMonitor
				.createPoint("CancionPerformance:obtenerDuracion");

		for (Cancion c : testElements) {
			c.obtenerDuracion();
		}

		point.collect();
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	@Test
	public void obtenerListaReproduccionPerformanceTest() {
		List<Cancion> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombre = cNameGen.next();
			Integer duracion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombre, duracion);

			testElements.add(cancion);
		}

		EtmPoint point = etmMonitor
				.createPoint("CancionPerformance:obtenerListaReproduccion");

		for (Cancion c : testElements) {
			c.obtenerListaReproduccion();
		}

		point.collect();
	}

	/**
	 * Buscar test.
	 */
	@Test
	public void buscarPerformanceTest() {
		List<Pair<Cancion, String>> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombre = cNameGen.next();
			Integer duracion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombre, duracion);

			testElements.add(new Pair<>(cancion, nombre));
		}

		EtmPoint point = etmMonitor.createPoint("CancionPerformance:buscar");

		for (Pair<Cancion, String> pair : testElements) {
			pair.getLeft().buscar(pair.getRight());
		}

		point.collect();
	}

}
