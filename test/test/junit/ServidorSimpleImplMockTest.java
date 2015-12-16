package test.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;



import org.junit.Test;
import org.mockito.Mockito;

import servidor.Servidor;
import servidor.ServidorSimpleImp;
import test.generators.ContenidoDuracionGenerator;
import test.generators.GeneralNameGenerator;
import test.generators.ServidorTokenGenerator;
import contenido.Anuncio;
import contenido.Cancion;
import contenido.Contenido;
import static org.mockito.Mockito.*;


public class ServidorSimpleImplMockTest {

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
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorSimpleImp = new ServidorSimpleImp(nombre,
				null, passwd, tokenValido);
		
		assertEquals(servidorSimpleImp.obtenerNombre(), nombre);
	}

	/**
	 * Agregar test.
	 */
	@Test
	public final void agregarConServidorSimpleImplMockTest() {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorSimpleImp = new ServidorSimpleImp(nombre,
				null, passwd, tokenValido);

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();
		
		Contenido cancionMock = mock(Cancion.class);
		when(cancionMock.obtenerTitulo()).thenReturn(titulo);
		when(cancionMock.obtenerDuracion()).thenReturn(duracion);

		servidorSimpleImp.agregar(cancionMock, passwd);
		List<Contenido> result = new ArrayList<>();
		result.add(cancionMock);
		assertEquals(servidorSimpleImp.buscar(titulo, tokenValido),result);
	}

	/**
	 * Eliminar test.
	 */
	@Test
	public final void eliminarConServidorSimpleImplMockTest() {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorSimpleImp = new ServidorSimpleImp(nombre,
				null, passwd, tokenValido);

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancionMock = mock(Cancion.class);
		when(cancionMock.obtenerTitulo()).thenReturn(titulo);
		when(cancionMock.obtenerDuracion()).thenReturn(duracion);

		servidorSimpleImp.agregar(cancionMock, passwd);
		
		List<Contenido> resultAntes = new ArrayList<>();
		resultAntes.add(cancionMock);
		assertEquals(servidorSimpleImp.buscar(titulo, tokenValido), resultAntes);

		servidorSimpleImp.eliminar(cancionMock, passwd);
		List<Contenido> resultDespois = new ArrayList<>();

		assertEquals(servidorSimpleImp.buscar(titulo, tokenValido), resultDespois);
	}

	/**
	 * Buscar token invalido test.
	 */
	@Test
	public final void buscarTokenInvalidoConServidorSimpleImplMockTest() {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorSimpleImp = new ServidorSimpleImp(nombre,
				null, passwd, tokenValido);

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancionMock = mock(Cancion.class);
		when(cancionMock.obtenerTitulo()).thenReturn(titulo);
		when(cancionMock.obtenerDuracion()).thenReturn(duracion);
		
		Contenido anuncioMock = mock(Anuncio.class);
		when(anuncioMock.obtenerTitulo()).thenReturn("PUBLICIDAD");
		
		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);

		String token = sTokenGen.next().getLeft();
		
		List<Contenido> result = new ArrayList<>();
		result = servidorSimpleImp.buscar(titulo, token);
		int i = 0;
		assertEquals(result.get(i).obtenerTitulo(),anuncioMock.obtenerTitulo());
		assertEquals(result.get(++i),cancionMock);
		assertEquals(result.get(++i),cancionMock);
		assertEquals(result.get(++i),cancionMock);
		assertEquals(result.get(++i).obtenerTitulo(),anuncioMock.obtenerTitulo());
		assertEquals(result.get(++i),cancionMock);
		assertEquals(result.get(++i),cancionMock);
		
		
	}

	/**
	 * Buscar con token válido test.
	 */
	@Test
	public final void buscarTokenValidoConServidorSimpleImplMockTest() {
		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorSimpleImp = new ServidorSimpleImp(nombre,
				null, passwd, tokenValido);

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancionMock = mock(Cancion.class);
		when(cancionMock.obtenerTitulo()).thenReturn(titulo);
		when(cancionMock.obtenerDuracion()).thenReturn(duracion);

		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);
		servidorSimpleImp.agregar(cancionMock, passwd);
		List<Contenido> result = new ArrayList<>();
		result.add(cancionMock);
		result.add(cancionMock);
		result.add(cancionMock);
		result.add(cancionMock);
		
		assertEquals(servidorSimpleImp.buscar(titulo, tokenValido), result);
	}

	/**
	 * Buscar con token valido más de diez elementos test.
	 */
	@Test
	public final void buscarTokenValidoMasDeDiezConServidorSimpleImplMockTest() {

		final int nItAgr = 15;
		final int nItBus = 10;

		String nombre = gNameGen.next();
		String passwd = gNameGen.next();
		String tokenValido = sTokenGen.next().getLeft();
		
		Servidor servidorSimpleImp = new ServidorSimpleImp(nombre,
				null, passwd, tokenValido);

		String titulo = gNameGen.next();
		Integer duracion = cDuracionGen.next();

		Contenido cancionMock = mock(Cancion.class);
		when(cancionMock.obtenerTitulo()).thenReturn(titulo);
		when(cancionMock.obtenerDuracion()).thenReturn(duracion);
		
		Contenido anuncioMock = mock(Anuncio.class);
		when(anuncioMock.obtenerTitulo()).thenReturn("PUBLICIDAD");

		for (int j = 0; j <= nItAgr; j++) {
			servidorSimpleImp.agregar(cancionMock, passwd);
		}
		List<Contenido> result = new ArrayList<>();
		result = servidorSimpleImp.buscar(titulo, tokenValido);
		int i;
		for (i = 0; i < nItBus; i++) {
			assertEquals(cancionMock,result.get(i));
		}
		
		assertTrue(anuncioMock.obtenerTitulo().compareTo(result.get(i).obtenerTitulo())==0);
		assertEquals(cancionMock,result.get(++i));
		assertEquals(cancionMock,result.get(++i));
		assertEquals(cancionMock,result.get(++i));
		assertTrue(anuncioMock.obtenerTitulo().compareTo(result.get(++i).obtenerTitulo())==0);
		assertEquals(cancionMock,result.get(++i));
		assertEquals(cancionMock,result.get(++i));
		assertEquals(cancionMock,result.get(++i));

	}
	
}
