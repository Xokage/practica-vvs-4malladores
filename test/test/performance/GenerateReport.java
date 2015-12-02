package test.performance;

import servidor.BackupServerException;
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
		AnuncioPerformance anuncio = new AnuncioPerformance();
		CancionPerformance cancion = new CancionPerformance();
		EmisoraPerformance emisora = new EmisoraPerformance();
		ServidorSimpleConRespaldoImpPerformance servidorSimpleConRespaldo = new ServidorSimpleConRespaldoImpPerformance();
		ServidorSimpleImplPerformance servidorSimple = new ServidorSimpleImplPerformance();

		// execute tests

		anuncio.obtenerTituloPerformanceTest();
		anuncio.obtenerDuracionPerformanceTest();
		anuncio.obtenerListaReproduccionPerformanceTest();
		anuncio.buscarPerformanceTest();

		cancion.obtenerTituloPerformanceTest();
		cancion.obtenerDuracionPerformanceTest();
		cancion.obtenerListaReproduccionPerformanceTest();
		cancion.buscarPerformanceTest();

		emisora.obtenerTituloPerformanceTest();
		emisora.obtenerDuracionPerformanceTest();
		emisora.obtenerListaReproduccionPerformanceTest();
		emisora.buscarPerformanceTest();
		emisora.agregarPerformanceTest();
		emisora.eliminarPerformanceTest();

		try {
			servidorSimpleConRespaldo.buscarPerformanceTest();
			servidorSimpleConRespaldo.buscarWithoutContentPerformanceTest();
		} catch (BackupServerException e) {
			e.printStackTrace();
		}

		servidorSimple.agregarPerformanceTest();
		servidorSimple.eliminarPerformanceTest();
		servidorSimple.buscarTokenInvalidoPerformanceTest();
		servidorSimple.buscarTokenValidoMasDeDiezPerformanceTest();
		servidorSimple.buscarTokenValidoPerformanceTest();

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
