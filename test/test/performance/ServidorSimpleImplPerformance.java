package test.performance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Cancion;
import contenido.Contenido;
import contenido.Emisora;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import servidor.Servidor;
import servidor.ServidorSimpleImp;
import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import test.generators.ServidorTokenGenerator;
import util.Pair;

/**
 * Tests para la clase Servidor Simple Impl.
 */
public class ServidorSimpleImplPerformance {

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator gNameGen = new GeneralNameGenerator();

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
	 * Obtener nombre test.
	 */
	public final void obtenerNombrePerformanceTest() {
		List<Servidor> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			Servidor s = new ServidorSimpleImp(nombre, null, "", "");
			testElements.add(s);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimple:obtenerTitulo");

		for (Servidor s : testElements) {
			s.obtenerNombre();
		}

		point.collect();
	}

	/**
	 * Agregar test.
	 */
	public final void agregarPerformanceTest() {
		List<Pair<Emisora, Cancion>> testElements = new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombreEmisora = gNameGen.next();
			Emisora emisora = new Emisora(nombreEmisora);

			String nombreCancion = gNameGen.next();
			Integer duracionCancion = cDuracionGen.next();
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			testElements.add(new Pair<>(emisora, cancion));
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimple:agregar");

		for (Pair<Emisora, Cancion> pair : testElements) {
			pair.getLeft().agregar(pair.getRight(), pair.getLeft());
		}

		point.collect();
	}

	/**
	 * Eliminar test.
	 */
	public final void eliminarPerformanceTest() {
		List<Pair<Servidor, Cancion>> testElements = new ArrayList<>();
		final String passwd = sTokenGen.next().getLeft();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			String token = sTokenGen.next().getLeft();
			Servidor s = new ServidorSimpleImp(nombre, null, passwd, token);
			String titulo = gNameGen.next();
			Integer duracion = cDuracionGen.next();
			Cancion cancion = new Cancion(titulo, duracion);
			s.agregar(cancion, passwd);
			testElements.add(new Pair<>(s, cancion));
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimple:eliminar");

		for (Pair<Servidor, Cancion> pair : testElements) {
			pair.getLeft().eliminar(pair.getRight(),passwd);
		}

		point.collect();
	}

	/**
	 * Buscar token invalido test.
	 */
	public final void buscarTokenInvalidoPerformanceTest() {
		List<Pair<Servidor, Pair<String, String>>> testElements = 
				new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			String passwd = gNameGen.next();
			String tokenValido = sTokenGen.next().getLeft();

			Servidor servidor = new ServidorSimpleImp(nombre, null, passwd,
					tokenValido);

			String titulo = gNameGen.next();
			String titulo1 = gNameGen.next();
			String titulo2 = gNameGen.next();

			Integer duracion = cDuracionGen.next();
			Integer duracion1 = cDuracionGen.next();
			Integer duracion2 = cDuracionGen.next();

			Contenido cancion = new Cancion(titulo, duracion);
			Contenido cancion1 = new Cancion(titulo1, duracion1);
			Contenido cancion2 = new Cancion(titulo2, duracion2);

			servidor.agregar(cancion, passwd);
			servidor.agregar(cancion1, passwd);
			servidor.agregar(cancion2, passwd);
			servidor.agregar(cancion2, passwd);

			testElements.add(new Pair<>(servidor, new Pair<>(titulo,
					tokenValido)));
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimple:buscarTokenInvalido");

		for (Pair<Servidor, Pair<String, String>> pair : testElements) {
			pair.getLeft().buscar(pair.getRight().getLeft(),
					sTokenGen.next().getLeft());
		}

		point.collect();
	}

	/**
	 * Buscar con token válido test.
	 */
	public final void buscarTokenValidoPerformanceTest() {
		List<Pair<Servidor, Pair<String, String>>> testElements = 
				new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			String passwd = gNameGen.next();
			String tokenValido = sTokenGen.next().getLeft();

			Servidor servidor = new ServidorSimpleImp(nombre, null, passwd,
					tokenValido);

			String titulo = gNameGen.next();
			String titulo1 = gNameGen.next();
			String titulo2 = gNameGen.next();

			Integer duracion = cDuracionGen.next();
			Integer duracion1 = cDuracionGen.next();
			Integer duracion2 = cDuracionGen.next();

			Contenido cancion = new Cancion(titulo, duracion);
			Contenido cancion1 = new Cancion(titulo1, duracion1);
			Contenido cancion2 = new Cancion(titulo2, duracion2);

			servidor.agregar(cancion, passwd);
			servidor.agregar(cancion1, passwd);
			servidor.agregar(cancion2, passwd);
			servidor.agregar(cancion2, passwd);

			testElements.add(new Pair<>(servidor, new Pair<>(titulo,
					tokenValido)));
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimple:buscarTokenValido");

		for (Pair<Servidor, Pair<String, String>> pair : testElements) {
			pair.getLeft().buscar(pair.getRight().getLeft(),
					pair.getRight().getRight());
		}

		point.collect();
	}

	/**
	 * Buscar con token valido más de diez elementos test.
	 */
	public final void buscarTokenValidoMasDeDiezPerformanceTest() {
		final int nIt = 15;
		List<Pair<Servidor, Pair<String, String>>> testElements = 
				new ArrayList<>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = gNameGen.next();
			String passwd = gNameGen.next();
			String tokenValido = sTokenGen.next().getLeft();

			Servidor servidor = new ServidorSimpleImp(nombre, null, passwd,
					tokenValido);

			String titulo = gNameGen.next();
			String titulo1 = gNameGen.next();
			String titulo2 = gNameGen.next();

			Integer duracion = cDuracionGen.next();
			Integer duracion1 = cDuracionGen.next();
			Integer duracion2 = cDuracionGen.next();

			Contenido cancion = new Cancion(titulo, duracion);
			Contenido cancion1 = new Cancion(titulo1, duracion1);
			Contenido cancion2 = new Cancion(titulo2, duracion2);

			for (int j = 0; j <= nIt; j++) {
				servidor.agregar(cancion, passwd);
				servidor.agregar(cancion1, passwd);
				servidor.agregar(cancion2, passwd);
			}
			testElements.add(new Pair<>(servidor, new Pair<>(titulo,
					tokenValido)));
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimple:buscarToken"
						+ "ValidoMasDeDiezElementos");

		for (Pair<Servidor, Pair<String, String>> pair : testElements) {
			pair.getLeft().buscar(pair.getRight().getLeft(),
					pair.getRight().getRight());
		}

		point.collect();
	}
	
	public void bajaPerformanceTest(){
		List<String> testElements = 
				new ArrayList<>();
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		Servidor servidor = new ServidorSimpleImp(nombre);
		for (int i = 0; i < itNumber; i++) {
			
			testElements.add(servidor.alta());
		}

		EtmPoint point = etmMonitor
				.createPoint("ServidorSimple:baja");

		for (String token : testElements) {
			servidor.baja(token);
		}

		point.collect();
	
	}
}
