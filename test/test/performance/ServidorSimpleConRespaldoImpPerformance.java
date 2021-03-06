package test.performance;

import java.util.ArrayList;
import java.util.List;

import servidor.BackupServerException;
import servidor.Servidor;
import servidor.ServidorSimpleConRespaldoImp;
import servidor.ServidorSimpleImp;
import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import test.generators.ServidorTokenGenerator;
import util.Pair;
import contenido.Cancion;
import contenido.Contenido;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

/**
 * Tests para la clase Servidor Simple con Respaldo.
 */
public class ServidorSimpleConRespaldoImpPerformance {

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator gNameGen = 
			new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen = 
			new ContenidoDuracionGenerator();

	/** Generador de duraciones válidas. */
	private final ServidorTokenGenerator sTokenGen = 
			new ServidorTokenGenerator();

	/** Number of iteratios to check performance. */
	private final Integer itNumber = 10000;

	/** JETM Monitor to check performance. */
	private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	/**
	 * Buscar test.
	 *
	 * @throws BackupServerException
	 *             la excepción BackupServerException
	 */
	public final void buscarPerformanceTest() throws BackupServerException {
		List<Pair<ServidorSimpleConRespaldoImp, Pair<String, String>>> testElements = 
				new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			String passwd = gNameGen.next();
			String tokenValido = sTokenGen.next().getLeft();

			Servidor servidorRespaldo = new ServidorSimpleImp(nombre, null,
					passwd, tokenValido);
			ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(
					nombre, null, passwd, tokenValido, servidorRespaldo);

			String titulo = gNameGen.next();
			String titulo1 = gNameGen.next();
			String titulo2 = gNameGen.next();

			Integer duracion = cDuracionGen.next();
			Integer duracion1 = cDuracionGen.next();
			Integer duracion2 = cDuracionGen.next();

			Contenido cancion = new Cancion(titulo, duracion);
			Contenido cancion1 = new Cancion(titulo1, duracion1);
			Contenido cancion2 = new Cancion(titulo2, duracion2);

			servidorRespaldo.agregar(cancion, passwd);
			servidorRespaldo.agregar(cancion1, passwd);
			servidorRespaldo.agregar(cancion2, passwd);
			servidorRespaldo.agregar(cancion2, passwd);

			testElements.add(new Pair<>(s1, new Pair<>(titulo, tokenValido)));
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimpleConRespaldo:buscar");

		for (Pair<ServidorSimpleConRespaldoImp, Pair<String, String>> pair : testElements) {
			pair.getLeft().buscar(pair.getRight().getLeft(),
					pair.getRight().getRight());
		}

		point.collect();
	}

	/**
	 * Buscar sin contenido test.
	 *
	 * @throws BackupServerException
	 *             la excepción BackupServerException
	 */
	public final void buscarWithoutContentPerformanceTest()
			throws BackupServerException {
		List<Pair<ServidorSimpleConRespaldoImp, String>> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			String passwd = gNameGen.next();
			String tokenValido = sTokenGen.next().getLeft();

			Servidor servidorRespaldo = new ServidorSimpleImp(nombre, null,
					passwd, tokenValido);
			ServidorSimpleConRespaldoImp s1 = new ServidorSimpleConRespaldoImp(
					nombre, null, passwd, tokenValido, servidorRespaldo);

			testElements.add(new Pair<>(s1, tokenValido));
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimpleConRespaldo:buscarVacio");

		for (Pair<ServidorSimpleConRespaldoImp, String> pair : testElements) {
			pair.getLeft().buscar(gNameGen.next(), pair.getRight());
		}

		point.collect();

	}
}
