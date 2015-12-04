package test.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import servidor.MockServidorSimpleImp;
import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import test.generators.ServidorTokenGenerator;
import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import static org.mockito.Mockito.*;


public class ServidorSimpleImplMockTest {
	
	/** Creamos un mock de servidorSimpleImp para probar. */
	MockServidorSimpleImp servidorSimpleImpMock = Mockito.mock(MockServidorSimpleImp.class);

	/** Generador de nombres válidos. */
	private final GeneralNameGenerator gNameGen = new GeneralNameGenerator();

	/** Generador de duraciones válidas. */
	private final ContenidoDuracionGenerator cDuracionGen = 
			new ContenidoDuracionGenerator();

	/** Generador de tokens válidos. */
	private final ServidorTokenGenerator sTokenGen = 
			new ServidorTokenGenerator();

	/**
	 * Obtener nombre test.
	 */
	@Test
	public final void obtenerNombreConServidorSimpleImplMockTest() {
		String nombre = gNameGen.next();
		when(servidorSimpleImpMock.obtenerNombre()).thenReturn(nombre);
		assertEquals(servidorSimpleImpMock.obtenerNombre(), nombre);
	}

	/**
	 * Agregar test.
	 */
	@Test
	public final void agregarConServidorSimpleImplMockTest() {
		String passwd = sTokenGen.next().getLeft();
		String token = sTokenGen.next().getLeft();

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();
		Contenido cancion = new Cancion(titulo, duracion);

		servidorSimpleImpMock.agregar(cancion, passwd);
		List<Contenido> result = new ArrayList<>();
		result.add(cancion);
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(result);
		assertEquals(servidorSimpleImpMock.buscar(titulo, token).get(0), cancion);
	}

	/**
	 * Eliminar test.
	 */
	@Test
	public final void eliminarConServidorSimpleImplMockTest() {
		String passwd = sTokenGen.next().getLeft();
		String token = sTokenGen.next().getLeft();

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancion = new Cancion(titulo, duracion);

		servidorSimpleImpMock.agregar(cancion, passwd);
		
		List<Contenido> resultAntes = new ArrayList<>();
		resultAntes.add(cancion);
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(resultAntes);

		servidorSimpleImpMock.eliminar(cancion, passwd);
		List<Contenido> resultDespois = new ArrayList<>();
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(resultDespois);

		assertEquals(resultAntes.get(0), cancion);
		assertEquals(resultDespois.size(), 0);
	}

	/**
	 * Buscar token invalido test.
	 */
	@Test
	public final void buscarTokenInvalidoConServidorSimpleImplMockTest() {
		String passwd = sTokenGen.next().getLeft();

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancion = new Cancion(titulo, duracion);
		Contenido anuncio = new Anuncio();
		
		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);

		List<Contenido> result = new ArrayList<>();
		result.add(anuncio);
		result.add(cancion);
		result.add(cancion);
		result.add(cancion);
		result.add(anuncio);
		String token = sTokenGen.next().getLeft();
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(result);
		
		assertEquals(servidorSimpleImpMock.buscar(titulo, token),result);
	}

	/**
	 * Buscar con token válido test.
	 */
	@Test
	public final void buscarTokenValidoConServidorSimpleImplMockTest() {
		String passwd = sTokenGen.next().getLeft();
		String token = sTokenGen.next().getLeft();

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancion = new Cancion(titulo, duracion);

		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);
		servidorSimpleImpMock.agregar(cancion, passwd);
		List<Contenido> result = new ArrayList<>();
		result.add(cancion);
		result.add(cancion);
		result.add(cancion);
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(result);
		assertEquals(servidorSimpleImpMock.buscar(titulo, token), result);
	}

	/**
	 * Buscar con token valido más de diez elementos test.
	 */
	@Test
	public final void buscarTokenValidoMasDeDiezConServidorSimpleImplMockTest() {

		final int nItAgr = 15;
		final int nItBus = 10;

		String passwd = sTokenGen.next().getLeft();
		String token = sTokenGen.next().getLeft();

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancion = new Cancion(titulo, duracion);
		Contenido anuncio = new Anuncio();

		for (int j = 0; j <= nItAgr; j++) {
			servidorSimpleImpMock.agregar(cancion, passwd);
		}
		List<Contenido> result = new ArrayList<>();
		int i;
		for (i = 0; i < nItBus; i++) {
			result.add(cancion);
		}
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(result);
		assertEquals(servidorSimpleImpMock.buscar(titulo, token),result);
		result.clear();
		result.add(anuncio);
		result.add(cancion);
		result.add(cancion);
		result.add(cancion);
		result.add(anuncio);
		
		when(servidorSimpleImpMock.buscar(titulo, token)).thenReturn(result);

		assertEquals(servidorSimpleImpMock.buscar(titulo, token),result);
	}
	
}
