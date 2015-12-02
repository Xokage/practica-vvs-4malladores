package test.performance;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.renderer.SimpleTextRenderer;

public class GenerateReport {

	private static EtmMonitor monitor;

	public static void main(String[] args) {
		// configure measurement framework
		setup();

		// instantiate tests
		AnuncioPerformance anuncioPerformance = new AnuncioPerformance();
		CancionPerformance cancionPerformance = new CancionPerformance();
		EmisoraPerformance emisoraPerformance = new EmisoraPerformance();
		
		// execute tests

		anuncioPerformance.obtenerTituloPerformanceTest();
		anuncioPerformance.obtenerDuracionPerformanceTest();
		anuncioPerformance.obtenerListaReproduccionPerformanceTest();
		anuncioPerformance.buscarPerformanceTest();

		cancionPerformance.obtenerTituloPerformanceTest();
		cancionPerformance.obtenerDuracionPerformanceTest();
		cancionPerformance.obtenerListaReproduccionPerformanceTest();
		cancionPerformance.buscarPerformanceTest();
		
		emisoraPerformance.obtenerTituloPerformanceTest();
		emisoraPerformance.obtenerDuracionPerformanceTest();
		emisoraPerformance.obtenerListaReproduccionPerformanceTest();
		emisoraPerformance.buscarPerformanceTest();
		emisoraPerformance.agregarPerformanceTest();
		emisoraPerformance.eliminarPerformanceTest();
		
		// visualize results
		monitor.render(new SimpleTextRenderer());

		// shutdown measurement framework
		tearDown();
	}

	private static void setup() {
		BasicEtmConfigurator.configure();
		monitor = EtmManager.getEtmMonitor();
		monitor.start();
	}

	private static void tearDown() {
		monitor.stop();
	}
}
