package test.performance;

import java.util.ArrayList;
import java.util.List;

import test.generators.GeneralNameGenerator;
import contenido.Anuncio;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * Tests de la clase Anuncio.
 */
public class AnuncioPerformance {

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator cNameGen = new GeneralNameGenerator();

	// Performance Variables

	/** Number of iteratios to check performance. */
	private final Integer itNumber = 10000;

	/** JETM Monitor to check performance. */
	private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	/**
	 * Obtener titulo test.
	 */
	public final void obtenerTituloPerformanceTest() {
		List<Anuncio> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			testElements.add(new Anuncio());
		}

		EtmPoint point = etmMonitor
				.createPoint("AnuncioPerformance:obtenerTitulo");

		for (Anuncio a : testElements) {
			a.obtenerTitulo();
		}

		point.collect();
	}

	/**
	 * Obtener duracion test.
	 */
	public final void obtenerDuracionPerformanceTest() {
		List<Anuncio> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			testElements.add(new Anuncio());
		}

		EtmPoint point = etmMonitor
				.createPoint("AnuncioPerformance:obtenerDuracion");

		for (Anuncio a : testElements) {
			a.obtenerDuracion();
		}

		point.collect();
	}

	/**
	 * Obtener lista reproduccion test.
	 */
	public final void obtenerListaReproduccionPerformanceTest() {
		List<Anuncio> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			Anuncio anuncio = new Anuncio();
			testElements.add(anuncio);
		}

		EtmPoint point = etmMonitor
				.createPoint("AnuncioPerformance:obtenerListaReproduccion");

		for (Anuncio a : testElements) {
			a.obtenerListaReproduccion();
		}

		point.collect();
	}

	/**
	 * Buscar test.
	 */
	public final void buscarPerformanceTest() {
		List<Anuncio> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			Anuncio anuncio = new Anuncio();
			testElements.add(anuncio);
		}

		EtmPoint point = etmMonitor.createPoint("AnuncioPerformance:buscar");

		for (Anuncio a : testElements) {
			a.buscar("PUBLICIDAD");
		}

		point.collect();
	}
	
	public final void buscarPerformanceTest2() {
		List<Anuncio> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			Anuncio anuncio = new Anuncio();
			testElements.add(anuncio);
		}

		EtmPoint point = etmMonitor.createPoint("AnuncioPerformance:buscar");
		
		String tituloAleatorio = cNameGen.next();
		String titulo = "PUBLICIDAD";
		while(titulo.contains(tituloAleatorio)) {
			tituloAleatorio = cNameGen.next();
		}
		for (Anuncio a : testElements) {
			a.buscar(tituloAleatorio);
		}

		point.collect();
	}
}
