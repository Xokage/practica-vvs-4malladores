package test.performance;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import contenido.Emisora;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import servidor.Servidor;
import servidor.ServidorSimpleImp;
import util.Pair;

/**
 * Tests para la clase Servidor Simple Impl.
 */
public class ServidorSimpleImplPerformancePerformance {

	/** Number of iteratios to check performance */
	private final Integer IT_NUMBER = 10000;

	/** JETM Monitor to check performance */
	private final EtmMonitor etmMonitor = EtmManager.getEtmMonitor();

	
	/**
	 * Obtener nombre test.
	 */
	public void obtenerNombrePerformanceTest() {
		List<Servidor> testElements = new ArrayList<>();
		for (int i = 0; i < IT_NUMBER; i++) {
			String nombre = "ServidorTest";
			Servidor s = new ServidorSimpleImp(nombre, null, "", "");
			testElements.add(s);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorSimpleImplPerformance:obtenerTitulo");

		for (Servidor s: testElements) {
			s.obtenerNombre();
		}

		point.collect();
	}

	/**
	 * Agregar test.
	 */
	@Test
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

		EtmPoint point = etmMonitor.createPoint("ServidorSimpleImplPerformance:agregar");

		for (Pair<Emisora, Cancion> pair : testElements) {
			pair.getLeft().agregar(pair.getRight(), pair.getLeft());
		}

		point.collect();

		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		Servidor s = new ServidorSimpleImp(nombre, null, passwd, token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, token);
		assertEquals(result.get(0), cancion);
	}

	/**
	 * Eliminar test.
	 */
	@Test
	public void eliminarPerformanceTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "imatoken";
		Servidor s = new ServidorSimpleImp(nombre, null, passwd, token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		List<Contenido> resultAntes = s.buscar(titulo, token);
		s.eliminar(cancion, passwd);
		List<Contenido> resultDespois = s.buscar(titulo, token);
		assertEquals(resultAntes.get(0), cancion);
		assertEquals(resultDespois.size(), 0);
	}

	/**
	 * Buscar token invalido test.
	 */
	@Test
	public void buscarTokenInvalidoPerformanceTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String token = "asdf";
		String tokenInvalido = "invalido";
		Servidor s = new ServidorSimpleImp(nombre, null, passwd, token);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		s.agregar(cancion, passwd);
		s.agregar(cancion, passwd);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, tokenInvalido);
		int i = 0;
		assertEquals(result.get(i++).obtenerTitulo(), "PUBLICIDAD");
		assertEquals(result.get(i++).obtenerTitulo(), titulo);
		assertEquals(result.get(i++).obtenerTitulo(), titulo);
		assertEquals(result.get(i++).obtenerTitulo(), titulo);
		assertEquals(result.get(i++).obtenerTitulo(), "PUBLICIDAD");
	}

	/**
	 * Buscar con token válido test.
	 */
	@Test
	public void buscarTokenValidoPerformanceTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String tokenValido = "valido";
		Servidor s = new ServidorSimpleImp(nombre, null, passwd, tokenValido);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		s.agregar(cancion, passwd);
		s.agregar(cancion, passwd);
		s.agregar(cancion, passwd);
		s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, tokenValido);
		int i = 0;
		assertEquals(titulo, result.get(i++).obtenerTitulo());
		assertEquals(titulo, result.get(i++).obtenerTitulo());
		assertEquals(titulo, result.get(i++).obtenerTitulo());
	}

	/**
	 * Buscar con token valido más de diez elementos test.
	 */
	@Test
	public void buscarTokenValidoMasDeDiezPerformanceTest() {
		String nombre = "ServidorTest";
		String passwd = "1234567890";
		String tokenValido = "valido";
		Servidor s = new ServidorSimpleImp(nombre, null, passwd, tokenValido);
		String titulo = "Baby";
		Contenido cancion = new Cancion(titulo, 60);
		for (int j = 0; j <= 15; j++)
			s.agregar(cancion, passwd);
		List<Contenido> result = s.buscar(titulo, tokenValido);
		int i;
		for (i = 0; i < 10; i++)
			assertEquals(titulo, result.get(i).obtenerTitulo());

		assertEquals("PUBLICIDAD", result.get(i++).obtenerTitulo());
		assertEquals(titulo, result.get(i++).obtenerTitulo());
		assertEquals(titulo, result.get(i++).obtenerTitulo());
		assertEquals(titulo, result.get(i++).obtenerTitulo());
		assertEquals("PUBLICIDAD", result.get(i).obtenerTitulo());
	}

}
